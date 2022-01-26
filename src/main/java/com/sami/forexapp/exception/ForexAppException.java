package com.sami.forexapp.exception;

public class ForexAppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ForexAppError error;

	public ForexAppException(ForexAppError error) {
		super(error.getErrorMsg());
		this.error = error;
	}

	public ForexAppError getError() {
		return error;
	}
}
