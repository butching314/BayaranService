package com.bayaran.service.configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bayaran.dao.AppUserClient;
import com.bayaran.dao.AppUserClientImpl;

@Configuration
public class DaoConfig {
	@Bean
	public DataSource dataSource() throws NamingException {
		InitialContext cxt = new InitialContext();
		return (DataSource) cxt.lookup( "java:/comp/env/jdbc/bayarandb" );
	}
	
	@Bean
	public AppUserClient appUserClient(final DataSource dataSource) {
		return new AppUserClientImpl(dataSource);
	}
}
