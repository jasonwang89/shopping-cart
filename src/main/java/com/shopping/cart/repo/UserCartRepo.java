package com.shopping.cart.repo;

import com.shopping.cart.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartRepo extends JpaRepository<UserCart, Long> {
	public UserCart findByCartIdAndItemId(long cartId, long itemId);

	public List<UserCart> findAllByUserIdAndCartIdAndIsPayed(long userId, long cartId, boolean isPayed);

	public void deleteAllByUserIdAndCartId(long userId, long cartId);
}
