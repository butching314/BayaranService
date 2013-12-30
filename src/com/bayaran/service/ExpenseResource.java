package com.bayaran.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.bayaran.dao.DaoException;
import com.bayaran.dao.ExpenseDaoClient;
import com.bayaran.dao.InvalidParameterException;
import com.bayaran.domain.Expense;

@Path("/expenses")
public class ExpenseResource extends BaseResource {
	private static final Logger LOGGER = Logger.getLogger(ExpenseResource.class);
	
	private final ExpenseDaoClient expenseDaoClient;
	
	public ExpenseResource(final ExpenseDaoClient expenseDaoClient) {
		this.expenseDaoClient = expenseDaoClient;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public List<Expense> getAllExpenses() throws DaoException {
		try {
			return expenseDaoClient.fetchAllExpenses();
		} catch (Exception ex) {
			throw createFault(ex);
		}
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public Expense insertNewExpense(final Expense expense) throws InvalidParameterException, DaoException {
		try {
			Validate.notNull(expense);
			return this.expenseDaoClient.create(expense);
		} catch (InvalidParameterException ex) {
			LOGGER.info(ex);
			throw createFault(ex);
		} catch (DaoException ex) {
			LOGGER.error(ex);
			throw createFault(ex);
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw createFault(ex);
		}
	}
}
