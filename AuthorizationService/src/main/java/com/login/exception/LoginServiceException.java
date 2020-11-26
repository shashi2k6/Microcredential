package com.login.exception;

public class LoginServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginServiceException(String message) {
		super(message);
	}

	public LoginServiceException(String message, String reference) {
		super(reference.concat(message));
	}

}
