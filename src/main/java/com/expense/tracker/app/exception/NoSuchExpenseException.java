package com.expense.tracker.app.exception;

public class NoSuchExpenseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchExpenseException() {
		
	}

	public NoSuchExpenseException(String message) {
		super(message);
	}
	
}
