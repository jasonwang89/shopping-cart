package com.shopping.cart.model;

public class Message {
	private String status;
	private String detail;

	public Message() {
	}

	public Message(String status, String detail) {
		this.status = status;
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
