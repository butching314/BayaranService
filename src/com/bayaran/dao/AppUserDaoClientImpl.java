package com.bayaran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bayaran.domain.User;

public class AppUserDaoClientImpl extends BaseDaoClient implements AppUserDaoClient {
	private static final BeanListHandler<User> USER_BEAN_HANDLER = new BeanListHandler<User>(User.class);
	
	private final DataSource dataSource;
	private final Map<String,Object> queries = loadYamlFile("/dao/AppUser.yaml");
	
	public AppUserDaoClientImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<User> fetchAllUsers() throws DaoException {
		Connection conn = null; 
		try {
			conn = dataSource.getConnection();
			
			QueryRunner run = new QueryRunner();        
	        return run.query(conn, (String) queries.get("AllUser"), USER_BEAN_HANDLER);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
}
