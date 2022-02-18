package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class FoodTypeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809001277192044884L;

	public FoodTypeNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
