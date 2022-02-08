
package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)

public class IdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975565477888174157L;

	public IdNotFoundException(String message) {
		super(message);
	}
}
