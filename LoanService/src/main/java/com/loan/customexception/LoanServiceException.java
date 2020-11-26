package com.loan.customexception;

public class LoanServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoanServiceException(String message) {
		super(message);
	}

	public LoanServiceException(String message, String reference) {
		super(reference.concat(message));
	}

}
