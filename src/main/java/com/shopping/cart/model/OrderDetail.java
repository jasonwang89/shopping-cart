package com.shopping.cart.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {

	private List<ItemData> itemDatas = new ArrayList<>();

	private long orderId;
	private double total;
	private long userId;
	private long cartId;
	private String status;

	public OrderDetail() {
	}

	public OrderDetail(
			List<ItemData> itemDatas,
			long orderId,
			double total,
			long userId,
			long cartId,
			String status) {
		this.itemDatas = itemDatas;
		this.orderId = orderId;
		this.total = total;
		this.userId = userId;
		this.cartId = cartId;
		this.status = status;
	}

	public List<ItemData> getItemDatas() {
		return itemDatas;
	}

	public void setItemDatas(List<ItemData> itemDatas) {
		this.itemDatas = itemDatas;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
