package com.shopping.cart.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus status;
	private String detail;

	public ErrorResponse(HttpStatus status, String detail) {
		this.status = status;
		this.detail = detail;
	}
}

