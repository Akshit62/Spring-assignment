/**
 * 
 */
package com.fabHotels.timetable.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fabHotels.timetable.exception.ApiError;

/**
 * @author akshitjain
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ NumberFormatException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ApiError handleWrongInputException(Exception ex, WebRequest request) {
		return new ApiError(HttpStatus.NOT_FOUND, "Wrong input exception", "Please stick to numbers only.");
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ApiError handleFalseDataException(Exception ex, WebRequest request) {
		return new ApiError(HttpStatus.NOT_FOUND, "False Data Entered", "Please try again with correct format.");
	}
	
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ApiError handleMediaTypeNotSupportedException(Exception ex, WebRequest request) {
		return new ApiError(HttpStatus.NOT_FOUND, ex.toString(), "Sorry, this media type is incorrect.");
	}
	
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ApiError handleInternalException(Exception ex, WebRequest request) {
		return new ApiError(HttpStatus.NOT_FOUND, ex.toString(), "Something Went Wrong.");
	}
}
