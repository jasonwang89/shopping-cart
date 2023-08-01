package com.shopping.cart.repo;

import com.shopping.cart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
	public Order findByOrderIdAndUserId(long orderId, long userId);

	public Order findByOrderId(long orderId);
}
