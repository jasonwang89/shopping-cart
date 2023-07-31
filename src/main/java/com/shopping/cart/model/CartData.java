package com.shopping.cart.model;

import java.util.ArrayList;
import java.util.List;

public class CartData {
	private List<ItemData> itemDatas = new ArrayList<>();
	private double total;
	private long userId;
	private long cartId;

	public CartData() {
	}

	public CartData(List<ItemData> itemDatas, double total, long userId, long cartId) {
		this.itemDatas = itemDatas;
		this.total = total;
		this.userId = userId;
		this.cartId = cartId;
	}

	public List<ItemData> getItemDatas() {
		return itemDatas;
	}

	public void setItemDatas(List<ItemData> itemDatas) {
		this.itemDatas = itemDatas;
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
}
