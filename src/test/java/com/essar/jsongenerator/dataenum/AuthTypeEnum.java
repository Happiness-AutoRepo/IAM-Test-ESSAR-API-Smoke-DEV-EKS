package com.essar.jsongenerator.dataenum;

public enum AuthTypeEnum {
	eAuth("eAuth"),
	SADI("SADI");
	
	private String value;

	private AuthTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
