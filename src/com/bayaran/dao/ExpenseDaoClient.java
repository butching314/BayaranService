package com.bayaran.dao;

import java.util.List;

import com.bayaran.domain.Expense;

public interface ExpenseDaoClient {
	List<Expense> fetchAllExpenses() throws DaoException;
	
	Expense create(final Expense expense) throws InvalidParameterException, DaoException;
}
