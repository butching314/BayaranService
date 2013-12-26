package com.bayaran.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

@Path("/hello")
public class HelloWorldService {	
	
	public HelloWorldService(){
	}
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		
		
		try {
			InitialContext cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/bayarandb" );
			
			final Connection conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			try {
				QueryRunner run = new QueryRunner();        
		        List<Map<String,Object>> result = run.query(conn, "select * from app_user", new MapListHandler());
		        
		        System.out.println(result);
			} finally {
				DbUtils.close(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  
		String output = "test";
		return Response.status(200).entity(output).build();
 
	}
}
