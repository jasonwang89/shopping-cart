package com.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_cart")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class UserCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long cartId;
	private long userId;
	private long itemId;
	private int itemNumber;
	private double itemTotal;
	private boolean isPayed;

	public UserCart() {
	}

	public UserCart(
			long id,
			long cartId,
			long userId,
			long itemId,
			int itemNumber,
			double itemTotal,
			boolean isPayed) {
		this.id = id;
		this.cartId = cartId;
		this.userId = userId;
		this.itemId = itemId;
		this.itemNumber = itemNumber;
		this.itemTotal = itemTotal;
		this.isPayed = isPayed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean payed) {
		isPayed = payed;
	}
}
