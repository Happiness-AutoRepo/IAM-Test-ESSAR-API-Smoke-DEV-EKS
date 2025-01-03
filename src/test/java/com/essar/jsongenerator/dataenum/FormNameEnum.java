package com.essar.jsongenerator.dataenum;

public enum FormNameEnum {
	
	_1040("1040"),
	_2048("2048"),
	PowerOfAttorney("2848"),
	TaxPayerInfoAuth("8821"),
	IVESRequestForTranscriptTaxReturn("4506C"),
	RequestForCollectionDueProcess("12153"),
	RequestForAppealsReview("12203");
	
	private String value;

	private FormNameEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
