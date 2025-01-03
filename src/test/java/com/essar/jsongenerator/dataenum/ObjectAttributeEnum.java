package com.essar.jsongenerator.dataenum;

/***
 * JSON Objects in the request 
 * 
 */
public enum ObjectAttributeEnum {

	Security_Object("security"),
	Proof_Object("proof"),
	Request_Object("request"),
	Form_Object("form"),
	Signatures_Object("signatures"),
	Attachments_Object("attachments"),
	Hashes_Object("hashes"),
	Codes_Object("codes"),
	Messages_Object("messages"),
	Item_Object("item"),
	
	
	ENV_User(System.getenv("USERNAME").toUpperCase() +"  ");
	
	

	private String value;

	private ObjectAttributeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
