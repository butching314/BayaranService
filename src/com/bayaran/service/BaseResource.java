package com.bayaran.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bayaran.dao.DaoException;
import com.bayaran.dao.InvalidParameterException;

public abstract class BaseResource {
	public enum CommonException {
		Common(Status.BAD_REQUEST, "00001", "Something failed"),		
		DaoCommons(Status.INTERNAL_SERVER_ERROR, "10001", "Dao Exception"),
		DaoParameter(Status.BAD_REQUEST, "10002", "Invalid paramter");

		private Status status;
		private String code;
		private String msg;
		
		private Response build() {
			return Response.status(status).entity(BayaranFault.createInstance(code, msg, null)).build();
		}
		
		private Response build(final Throwable throwable) {
			final BayaranFault fault = BayaranFault.createInstance(code, msg, throwable);
			return Response.status(status).entity(fault).build();
		}

		private CommonException(final Status status, final String code, final String msg) {
			this.status = status;
			this.code = code;
			this.msg = msg;
		}

		public WebApplicationException createFault() {
			return BayaranFault.createFault(build());
		}
		
		public WebApplicationException createFault(final Throwable throwable) {
			return BayaranFault.createFault(throwable, build(throwable));
		}
	}
	
	protected static WebApplicationException createFault (final InvalidParameterException ex) {
		return CommonException.DaoParameter.createFault(ex);
	}
	
	protected static WebApplicationException createFault (final DaoException ex) {
		return CommonException.DaoCommons.createFault();
	}
	
	protected static WebApplicationException createFault (final Exception ex) {
		return CommonException.Common.createFault(ex);
	}
}
