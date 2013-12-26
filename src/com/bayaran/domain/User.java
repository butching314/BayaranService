package com.bayaran.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String userId;
	private String lastName;
	private String firstName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
