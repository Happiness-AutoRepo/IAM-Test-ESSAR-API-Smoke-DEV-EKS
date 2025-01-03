package com.essar.utils;

public enum ResponseBodyType {
	SAVE("Save"), GET("Get"), GETBYTTID("GetBYTT&ID"), INTENTBYID("IntentByIdResponse"), SAVEINTENT("Save Intent");
	
	private String value;

	private ResponseBodyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

