package com.shopping.cart.advice;

import com.shopping.cart.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ShoppingCartAdvice {

	@ExceptionHandler({EntityNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ee) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "Entity not found"), HttpStatus.NOT_FOUND);
	}
}
