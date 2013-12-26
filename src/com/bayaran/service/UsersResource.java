package com.bayaran.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.bayaran.dao.AppUserClient;
import com.bayaran.domain.DaoDomainConverter;
import com.bayaran.domain.User;

@Path("/users")
public class UsersResource extends BaseResource {
	private static final Logger LOGGER = Logger.getLogger(UsersResource.class);
	private final AppUserClient appUserClient;
	
	public UsersResource(final AppUserClient appUserClient) {
		this.appUserClient = appUserClient;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public List<User> getAllUsers() throws Exception {
		return DaoDomainConverter.toDomainUserList(this.appUserClient.fetchAllUsers());
	}
}
