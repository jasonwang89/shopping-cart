package com.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shopping.cart.enums.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_data")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long orderId;
	private long userId;

	private long cartId;

	private double total;

	private OrderStatus status;


	public Order() {
	}

	public Order(long id, long orderId, long userId, long cartId, double total) {
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.cartId = cartId;
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override public String toString() {
		return
				"order number=" + orderId +
				", total charge=" + total +
				", order status=" + status ;
	}
}

