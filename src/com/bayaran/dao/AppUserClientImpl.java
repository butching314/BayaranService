package com.bayaran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.yaml.snakeyaml.Yaml;

public class AppUserClientImpl implements AppUserClient {
	final DataSource dataSource;
	final Map<String,String> queries = initQueries();
	
	@SuppressWarnings("unchecked")
	private static Map<String,String> initQueries() {
		return (Map<String, String>) new Yaml().load(AppUserClientImpl.class.getResourceAsStream("/dao/AppUser.yaml"));
	}
	
	public AppUserClientImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<AppUser> fetchAllUsers() throws DaoException {
		Connection conn = null; 
		try {
			conn = dataSource.getConnection();
			
			QueryRunner run = new QueryRunner();        
	        return run.query(conn, queries.get("AllUser"), new BeanListHandler<AppUser>(AppUser.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
}
