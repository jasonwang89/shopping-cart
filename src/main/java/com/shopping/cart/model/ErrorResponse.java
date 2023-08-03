package com.shopping.cart.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus status;
	private String detail;

	public ErrorResponse(HttpStatus status, String detail) {
		this.status = status;
		this.detail = detail;
	}

	public ErrorResponse() {
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}

