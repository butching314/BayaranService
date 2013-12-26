package com.bayaran.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import com.bayaran.domain.User;

@Path("/users")
public class UsersResource extends BaseResource {
	private static final Logger LOGGER = Logger.getLogger(UsersResource.class);
	private final DataSource dataSource;
	
	public UsersResource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static String QRY_SELECT_ALL = 
			"select user_id userId," +
			"first_name firstName," +
			"last_name lastName " +
			"from app_user";
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public List<User> getAllUsers() throws Exception {
		LOGGER.warn("HEY");
		Connection conn = null; 
		try {
			conn = dataSource.getConnection();
			
			QueryRunner run = new QueryRunner();        
	        return run.query(conn, QRY_SELECT_ALL, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
	}
}
