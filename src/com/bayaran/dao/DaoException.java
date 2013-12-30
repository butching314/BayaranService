package com.bayaran.dao;

public class DaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(final Throwable throwable) {
		super(throwable);
	}
	
	public DaoException(final String message) {
		super(message);
	}
}
