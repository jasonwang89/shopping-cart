package com.shopping.cart.services;

import com.shopping.cart.enums.OrderStatus;
import com.shopping.cart.constant.ShoppingCartConstant;
import com.shopping.cart.entity.Item;
import com.shopping.cart.entity.Order;
import com.shopping.cart.entity.Storage;
import com.shopping.cart.entity.User;
import com.shopping.cart.entity.UserCart;
import com.shopping.cart.exception.VersionException;
import com.shopping.cart.model.CartData;
import com.shopping.cart.model.ItemData;
import com.shopping.cart.model.Message;
import com.shopping.cart.model.OrderDetail;
import com.shopping.cart.model.TotalAmount;
import com.shopping.cart.model.ValidateVersion;
import com.shopping.cart.repo.ItemRepo;
import com.shopping.cart.repo.OrderRepo;
import com.shopping.cart.repo.StorageRepo;
import com.shopping.cart.repo.UserCartRepo;
import com.shopping.cart.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

import static com.shopping.cart.constant.ShoppingCartConstant.*;

@Service
public class ShoppingCartService {

	private Random random = new Random();

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserCartRepo userCartRepo;

	public List<Item> getItems() {
		return itemRepo.findAll();
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	public int getStorage(final Long itemId) {
		Storage storage = storageRepo.getById(itemId);
		return storage.getTotal();
	}

	public Item getItem(final long itemId) throws EntityNotFoundException{
		return itemRepo.getById(itemId);
	}

	@Transactional
	public Message modifyItemInCart(final String itemId, final int num, final long userId, final String cartId) {
		Message message = new Message("", "");
		UserCart userCart;
		Item item = getItem(Long.valueOf(itemId));
		long cartNum = 0;
		if(!validateStorage(item.getItemId(), num, message).isValid()) {
			return message;
		}
		if(cartId == null || cartId.isEmpty()) {
			cartNum = Math.abs(random.nextInt(1000000));
			userCart = initializeCart(userId, cartNum, item, num);
			userCartRepo.save(userCart);
		} else {
			userCart = userCartRepo.findByCartIdAndItemId(Long.valueOf(cartId), Long.valueOf(itemId));
			if(userCart == null) {
				//create new row in userCart
				userCart = initializeCart(userId, Long.valueOf(cartId), item, num);
				userCartRepo.save(userCart);
			}else if(num != userCart.getItemNumber()) {
				//update exist row in userCart
				userCart.setItemNumber(num);
				userCart.setItemTotal(item.getPrice()*num);
				userCartRepo.save(userCart);
			}
		}
		message.setStatus(ShoppingCartConstant.SUCCESS);
		message.setDetail(String.format(CREATE_OR_UPDATE_CART, userCart.getCartId()));
		return message;
	}

	@Transactional
	public Message placeOrder(String userId, String cartId, String cardNo, String expireDate, String cvc) {
		Message message = new Message("", "");
		if(!validatePayment(Long.valueOf(userId), cardNo, expireDate, cvc)) {
			message.setStatus(ShoppingCartConstant.FAILURE);
			message.setDetail(String.format(USER_PAYMENT_METHOD_NOT_VALID, userId));
			return message;
		}
		final TotalAmount totalAmount = new TotalAmount(0);
		List<UserCart> itemsInCart = userCartRepo.findAllByUserIdAndCartIdAndIsPayed(Long.valueOf(userId), Long.valueOf(cartId), false);
		itemsInCart.stream().forEach(a-> {
			try {
				ValidateVersion validateVersion = validateStorage(a.getItemId(),a.getItemNumber(), message);
				if(validateVersion.isValid()) {
					double total = totalAmount.getTotal()+ a.getItemTotal();
					totalAmount.setTotal(total);
					updateItemStorage(a.getItemId(), a.getItemNumber(), validateVersion.getVersion());
					updateUserCartItemsStatus(a);
				}
			} catch(VersionException ve) {
				String detail = message.getDetail();
				detail += String.format(STORAGE_IS_CHANGE, a.getItemId());
				message.setDetail(detail);
			}
		});
		if(!itemsInCart.isEmpty()) {
			Order response = createOrder(userId, cartId, totalAmount.getTotal());
			message.setStatus(SUCCESS);
			message.setDetail(response.toString());
		} else{
			message.setStatus(FAILURE);
			message.setDetail(NO_ITEMS_PURCHASED);
		}

		return message;
	}

	private Order createOrder(String userId, String cartId, double total) {
		Order order = new Order();
		order.setOrderId(random.nextInt(99999999));
		order.setUserId(Long.valueOf(userId));
		order.setCartId(Long.valueOf(cartId));
		order.setTotal(total);
		order.setStatus(OrderStatus.NEW.toString());
		return orderRepo.save(order);
	}

	private void updateUserCartItemsStatus(UserCart userCart) {
		userCart.setPayed(true);
		userCartRepo.save(userCart);
	}

	private void updateItemStorage(long itemId, int itemNumber, int version) {
		Storage storage = storageRepo.getById(itemId);
		if(version != storage.getVersion()) {
			throw new VersionException("version doesn't match");
		} else {
			storage.setTotal(storage.getTotal()-itemNumber);
			storage.setVersion(version+1);
			storageRepo.save(storage);
		}
	}

	private boolean validatePayment(long userId, String cardNo, String expireDate, String cvc) {
		User user = userRepo.findByUserId(userId);
		return user.getCardNo().equals(cardNo) && user.getExpireDate().equals(expireDate) && user.getCvc().equals(cvc);
	}

	private ValidateVersion validateStorage(final long itemId, final int num, final Message message) {
		Storage storage = storageRepo.getById(itemId);
		ValidateVersion validateVersion = new ValidateVersion();
		String detail = message.getDetail();
		if(num > storage.getTotal()) {
			message.setStatus(ShoppingCartConstant.FAILURE);
			detail += String.format(storage.getTotal() == 0? ShoppingCartConstant.OUT_OF_STOCK :ShoppingCartConstant.ORDER_NUMBER_OVER_STOCK, itemId);
			message.setDetail(detail);
			validateVersion.setValid(false);
			validateVersion.setVersion(storage.getVersion());
			return validateVersion;
		}
		validateVersion.setValid(true);
		validateVersion.setVersion(storage.getVersion());
		return validateVersion;
	}

	private UserCart initializeCart(long userId, long cartNum, Item item, int num) {
		UserCart userCart = new UserCart();
		userCart.setUserId(userId);
		userCart.setCartId(cartNum);
		userCart.setItemId(item.getItemId());
		userCart.setItemNumber(num);
		userCart.setItemTotal(item.getPrice()*num);
		return userCart;
	}

	@Transactional
	public void clearCart(String userId, String cartId) {
		userCartRepo.deleteAllByUserIdAndCartId(Long.valueOf(userId), Long.valueOf(cartId));
	}

	public CartData getAllItemsInCart(String userId, String cartId) {
		CartData cartData = new CartData();
		List<UserCart> items = userCartRepo.findAllByUserIdAndCartIdAndIsPayed(Long.valueOf(userId),Long.valueOf(cartId), false);
		cartData.setCartId(Long.valueOf(cartId));
		cartData.setUserId(Long.valueOf(userId));
		TotalAmount totalAmount = new TotalAmount(0);
		items.stream().forEach(a-> {
			ItemData itemData = new ItemData();
			itemData.setItemId(a.getItemId());
			Item item = getItem(a.getItemId());
			itemData.setItemName(item.getName());
			itemData.setCount(a.getItemNumber());
			itemData.setSinglePrice(item.getPrice());
			itemData.setTotal(itemData.getSinglePrice() * itemData.getCount());
			List<ItemData> itemData1 = cartData.getItemDatas();
			itemData1.add(itemData);
			totalAmount.setTotal(totalAmount.getTotal() + itemData.getTotal());
			cartData.setItemDatas(itemData1);
			cartData.setTotal(totalAmount.getTotal());
		});
		return cartData;
	}

	public OrderDetail getOrderDetail(String orderId) {
		Order order = orderRepo.findByOrderId(Long.valueOf(orderId));
		OrderDetail orderDetail = new OrderDetail();
		List<UserCart> items = userCartRepo.findAllByUserIdAndCartIdAndIsPayed(order.getUserId(),order.getCartId(), true);
		orderDetail.setCartId(order.getCartId());
		orderDetail.setUserId(order.getUserId());
		orderDetail.setOrderId(order.getOrderId());
		orderDetail.setStatus(order.getStatus());
		TotalAmount totalAmount = new TotalAmount(0);
		items.stream().forEach(a-> {
			ItemData itemData = new ItemData();
			itemData.setItemId(a.getItemId());
			Item item = getItem(a.getItemId());
			itemData.setItemName(item.getName());
			itemData.setCount(a.getItemNumber());
			itemData.setSinglePrice(item.getPrice());
			itemData.setTotal(itemData.getSinglePrice() * itemData.getCount());
			List<ItemData> itemData1 = orderDetail.getItemDatas();
			itemData1.add(itemData);
			totalAmount.setTotal(totalAmount.getTotal() + itemData.getTotal());
			orderDetail.setItemDatas(itemData1);
			orderDetail.setTotal(totalAmount.getTotal());
		});
		return orderDetail;
	}
}
