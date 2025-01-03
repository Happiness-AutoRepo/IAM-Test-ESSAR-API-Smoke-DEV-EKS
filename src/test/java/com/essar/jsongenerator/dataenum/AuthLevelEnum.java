package com.essar.jsongenerator.dataenum;

public enum AuthLevelEnum {
	LoA3("LoA3"),
	IAL2("IAL2");
	
	private String value;

	private AuthLevelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
