package com.expense.tracker.app.exception;

public class InvalidInputException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		
	}
	
       public InvalidInputException(String message) {
    	   super(message);
		
	}
	
	
}
