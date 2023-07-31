package com.shopping.cart.model;

public class ItemData {
	private long itemId;
	private String itemName;
	private double singlePrice;
	private double total;
	private int count;

	public ItemData() {
	}

	public ItemData(long itemId, String itemName, double singlePrice, double total, int count) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.singlePrice = singlePrice;
		this.total = total;
		this.count = count;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
