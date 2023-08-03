package com.shopping.cart.controller;

import com.shopping.cart.entity.Item;
import com.shopping.cart.entity.Order;
import com.shopping.cart.entity.User;
import com.shopping.cart.model.CartData;
import com.shopping.cart.model.ErrorResponse;
import com.shopping.cart.model.Message;
import com.shopping.cart.model.OrderDetail;
import com.shopping.cart.services.ShoppingCartService;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class ShoppingCartController {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILURE = "FAILURE";

	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping(path = "/storage/{itemId}")
	public int checkStorage(@PathVariable String itemId) {
		return shoppingCartService.getStorage(Long.valueOf(itemId));
	}

	@GetMapping(path = "/item/{itemId}", produces = "application/json;charset=utf-8")
	public ResponseEntity<?> getItemDetail(@PathVariable String itemId) {
		return new ResponseEntity<>(shoppingCartService.getItem(Long.valueOf(itemId)), HttpStatus.OK);
	}

	@GetMapping(path = "/items")
	public List<Item> getItems() {
		return shoppingCartService.getItems();
	}

	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return shoppingCartService.getUsers();
	}

	@GetMapping(path = "/orders")
	public List<Order> getAllOrders() {
		return shoppingCartService.getAllOrders();
	}

	@PutMapping(path ="/cart", produces = "application/json;charset=utf-8")
	public Message modifyItemCart(@RequestParam String itemId, @RequestParam String number, @RequestParam String userId, @RequestParam @Nullable
	String cartId) {
		return shoppingCartService.modifyItemInCart(itemId, Integer.valueOf(number), Long.valueOf(userId), cartId);
	}

	@GetMapping(path="/cart", produces = "application/json;charset=utf-8")
	public CartData getAllItemsInCart(@RequestParam String userId, @RequestParam String cartId) {
		return shoppingCartService.getAllItemsInCart(userId, cartId);
	}

	@PutMapping(path="/cart/clear", produces = "application/json;charset=utf-8")
	public void clearCart(@RequestParam String userId, @RequestParam String cartId) {
		shoppingCartService.clearCart(userId, cartId);
	}

	@PutMapping(path = "/order/place", produces = "application/json;charset=utf-8")
	public Message placeOrder(@RequestParam String userId, @RequestParam String cartId, @RequestParam String cardNo, @RequestParam String expireDate, @RequestParam String cvc) {
		return shoppingCartService.placeOrder(userId, cartId, cardNo, expireDate, cvc);
	}

	@GetMapping(path = "/order/{orderId}", produces = "application/json;charset=utf-8")
	public OrderDetail getOrderDetail(@PathVariable String orderId) {
		return shoppingCartService.getOrderDetail(orderId);
	}
}
