package com.bayaran.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.bayaran.dao.TestDao;

@Path("/hello")
public class HelloWorldService {	
	private final TestDao testDao;
	
	public HelloWorldService(final TestDao testDao){
		this.testDao = testDao;
	}
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Marvin will say ["+this.hashCode()+"]["+testDao+"]: " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
}
