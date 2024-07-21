package com.kodnest.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APIException extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String message;
	
	public APIException(String message) {
		super(message);
		this.message = message;
	}
}







