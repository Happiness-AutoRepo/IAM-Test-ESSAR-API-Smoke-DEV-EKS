package com.essar.jsongenerator.dataenum;

/**
 *	This enum tracks all the attribute names for the json request
 */
public enum AttributeEnum {
	security("security"),
	token("token"),
	form("form"),
	formId("formId"),
	formHash("formHash"),
	businessName("businessName"),
	ipAddress("ipAddress"),
	sessionId("sessionId"),
	userId("userId"),
	userIdType("userIdType"),
	sorTimestamp("sorTimestamp"),
	intentId("intentId"),
	userAgent("userAgent"),
	authId("authId"),
	sor("sor"),
	name("name"),
	position("position"),
	authType("authType"),
	authLevel("authLevel"),
	email("email"),
	data("data"),
	storageLocation("storageLocation"),
	storageId("storageId"),
	version("version"),
	correlatedIds("correlatedIds"),
	hash("hash"),
	id("id"),
	
	//Hashes
	correlatedId("correlatedId"),
	type("type"),
	value("value"),
	
	//Response
	savedSignatures("savedSignatures"),
	totalSignatures("totalSignatures"),
	savedAttachments("savedAttachments"),
	totalAttachments("totalAttachments");
	
	private String valueOfEnum;

	private AttributeEnum(String value) {
		this.valueOfEnum = value;
	}

	public String getValue() {
		return valueOfEnum;
	}

}
