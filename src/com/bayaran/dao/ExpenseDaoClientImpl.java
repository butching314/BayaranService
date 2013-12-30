package com.bayaran.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.Validate;

import com.bayaran.domain.Expense;
import com.bayaran.domain.User;

public class ExpenseDaoClientImpl extends BaseDaoClient implements ExpenseDaoClient {
	private final DataSource dataSource;
	private final Map<String,Object> yamlConfig = loadYamlFile("/dao/Expense.yaml");
	
	private final BeanListHandler<Expense> allExpensesBeanListHandler;
	private final BeanHandler<Expense> expensesBeanHandler;
	
	@SuppressWarnings("unchecked")
	public ExpenseDaoClientImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		
		final BeanProcessor allExpensesBeanProcessor = new BeanProcessor((Map<String, String>) yamlConfig.get("AllExpenses.BeanMapping"));
		allExpensesBeanListHandler = new BeanListHandler<Expense>(Expense.class, new ExpenseBeanProcessor(allExpensesBeanProcessor));
		
		final BeanProcessor expensesBeanProcessor = new BeanProcessor((Map<String, String>) yamlConfig.get("LastInsertedExpense.BeanMapping"));
		expensesBeanHandler = new BeanHandler<Expense>(Expense.class, new ExpenseBeanProcessor(expensesBeanProcessor));
	}
	
	@Override
	public List<Expense> fetchAllExpenses() throws DaoException {		
		Connection conn = null; 
		try {
			conn = dataSource.getConnection();

			QueryRunner run = new QueryRunner();        
	        return run.query(conn, (String) yamlConfig.get("AllExpenses"), allExpensesBeanListHandler);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	private static class ExpenseBeanProcessor extends CustomRowProcessor {
		public ExpenseBeanProcessor(final BeanProcessor beanProcessor) { 
			super(beanProcessor);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {
			final Expense expense = super.toBean(rs, Expense.class);
			expense.setPayor(super.toBean(rs,  User.class));
			return (T) expense;
		}
	}

	@Override
	public Expense create(final Expense expense) throws InvalidParameterException, DaoException {
		this.validateExpenseBeforeCreate(expense);
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			final String qry = (String) yamlConfig.get("CreateExpense");
			
			QueryRunner run = new QueryRunner();        
	        int result = run.update(conn, qry, expense.getTrxDate(), expense.getAmount(),
	        		expense.getStore(), expense.getDescription(), 
	        		expense.getRemark(), expense.getPayor().getUserId());
	        
	        if (result == 1) {
		        return run.query(conn, (String) yamlConfig.get("LastInsertedExpense"), expensesBeanHandler);
	        }
	        
	        return null;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	private void validateExpenseBeforeCreate(final Expense expense) throws InvalidParameterException {
		try {
			Validate.notNull(expense, "Expense object required");
			Validate.notNull(expense.getPayor(), "Payor is missing");			
		} catch (final IllegalArgumentException ex) {
			throw new InvalidParameterException(ex);
		}
	}
}