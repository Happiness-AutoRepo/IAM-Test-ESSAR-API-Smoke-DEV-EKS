package com.essar.jsongenerator.dataenum;

/***
 * JSON Objects in the request 
 * JSON attributes in the request
 */
public enum StatusCodeEnum {

	RetriveSuccessReturnCode("2-2C");
	
	
	
	
	
	private String value;

	private StatusCodeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
