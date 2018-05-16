package com.king.flights.controllers;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationError(ValidationException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleError(Exception exception) {
		logger.error(exception.getMessage(), exception);
		return new ResponseEntity<Object>("Unexpected error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
