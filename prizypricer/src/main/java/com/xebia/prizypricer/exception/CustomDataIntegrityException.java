package com.xebia.prizypricer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomDataIntegrityException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public CustomDataIntegrityException(String exception) {
		super(exception);
	}
}
