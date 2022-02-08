package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)

public class AlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5671382148377102652L;

	public AlreadyExistsException(String message) {
		super(message);
	}
}
