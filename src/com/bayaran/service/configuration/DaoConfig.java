package com.bayaran.service.configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bayaran.dao.AppUserDaoClient;
import com.bayaran.dao.AppUserDaoClientImpl;
import com.bayaran.dao.ExpenseDaoClient;
import com.bayaran.dao.ExpenseDaoClientImpl;

@Configuration
public class DaoConfig {
	@Bean
	public DataSource dataSource() throws NamingException {
		InitialContext cxt = new InitialContext();
		return (DataSource) cxt.lookup( "java:/comp/env/jdbc/bayarandb" );
	}
	
	@Bean
	public AppUserDaoClient appUserDaoClient(final DataSource dataSource) {
		return new AppUserDaoClientImpl(dataSource);
	}
	
	@Bean
	public ExpenseDaoClient expenseDaoClient(final DataSource dataSource) {
		return new ExpenseDaoClientImpl(dataSource);
	}
}
