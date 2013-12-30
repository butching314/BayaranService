package com.bayaran.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.bayaran.dao.AppUserDaoClient;
import com.bayaran.domain.User;

@Path("/users")
public class UserResource extends BaseResource {
	private static final Logger LOGGER = Logger.getLogger(UserResource.class);
	
	private final AppUserDaoClient appUserDaoClient;
	
	public UserResource(final AppUserDaoClient appUserClient) {
		this.appUserDaoClient = appUserClient;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public List<User> getAllUsers() throws Exception {
		try {
			return this.appUserDaoClient.fetchAllUsers();			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw createFault(ex);
		}
		
	}
}
