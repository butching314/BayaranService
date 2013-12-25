package com.bayaran.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {	
	
	public HelloWorldService(){
	}
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
  
		String output = "test";
		return Response.status(200).entity(output).build();
 
	}
}
