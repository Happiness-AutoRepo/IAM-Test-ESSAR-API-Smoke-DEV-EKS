package com.essar.jsongenerator.dataenum;

public enum TransTypeEnum {

	CAF_DLN("CAL_DLN"),
	IVES_TransID_Default("ives_transid"),
	SDLN_Retired("SDLN");
	
	private String value;

	private TransTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
