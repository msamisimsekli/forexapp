package com.sami.forexapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ForexAppExceptionHandler {

	@ExceptionHandler(ForexAppException.class)
	public ResponseEntity<?> forexAppExceptionHandling(ForexAppException exception, WebRequest request) {
		return new ResponseEntity<>(exception.getError().getErrorMsg(), exception.getError().getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}