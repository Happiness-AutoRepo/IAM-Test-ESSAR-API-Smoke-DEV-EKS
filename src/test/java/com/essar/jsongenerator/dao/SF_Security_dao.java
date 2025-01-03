package com.essar.jsongenerator.dao;

public class SF_Security_dao {
	
	private String token;
	// Class marked for deletion
	public SF_Security_dao(){
		this.token = "noToken";
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
