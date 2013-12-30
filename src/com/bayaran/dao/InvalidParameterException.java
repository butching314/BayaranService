package com.bayaran.dao;

public class InvalidParameterException extends DaoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidParameterException(final String message) {
		super(message);
	}
	
	public InvalidParameterException(final Throwable throwable) {
		super(throwable);
	}
}
