package com.sami.forexapp.exception;

import org.springframework.http.HttpStatus;

public enum ForexAppError {

	EXCHAGE_RATES_API_ERROR("API error", HttpStatus.INTERNAL_SERVER_ERROR),
	UNACCEPTED_BASE_VALUE("Unaccepted base value", HttpStatus.BAD_REQUEST),
	UNACCEPTED_SYMBOL_VALUE("Unaccepted symbol value", HttpStatus.BAD_REQUEST),
	CONVERSION_ID_NOT_FOUND("Conversion id not found", HttpStatus.BAD_REQUEST),
	UNACCEPTED_DATE_TIME_VALUE("Unaccepted local date time value", HttpStatus.BAD_REQUEST),
	UNACCEPTED_AMOUNT_TO_CONVERT("Unaccepted amount to convert", HttpStatus.BAD_REQUEST);

	private ForexAppError(String msg, HttpStatus httpStatus) {
		this.errorMsg = msg;
		this.httpStatus = httpStatus;
	}

	private String errorMsg;
	private HttpStatus httpStatus;

	public String getErrorMsg() {
		return errorMsg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
