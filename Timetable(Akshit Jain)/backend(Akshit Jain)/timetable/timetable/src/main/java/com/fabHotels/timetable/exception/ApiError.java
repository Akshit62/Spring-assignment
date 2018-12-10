package com.fabHotels.timetable.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
	HttpStatus httpStatus;
	String exception;
	String msg;

	public ApiError(HttpStatus httpStatus, String exepction, String msg) {
		super();
		this.httpStatus = httpStatus;
		this.exception = exepction;
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exepction) {
		this.exception = exepction;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

