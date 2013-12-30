package com.bayaran.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class BayaranFault {
	public static BayaranFault createInstance(String code, String msg, Throwable throwable) {
		BayaranFault e = new BayaranFault();
		e.errorCode = code;
		e.errorMsg = msg;
		
		if (throwable != null) {
			e.cause = throwable.getMessage();
		}
		
		return e;
	}
	
	public static WebApplicationException createFault(final Response response) {
		return new WebApplicationException(response);
	}
	
	public static WebApplicationException createFault(final Throwable throwable, final Response response) {
		return new WebApplicationException(throwable, response);
	}

	public BayaranFault() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		errorMsg = errorMsg;
	}

	private String errorCode;
	private String errorMsg;
	private String cause;

	public String toString() {
		return errorCode + " : " + errorMsg;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
