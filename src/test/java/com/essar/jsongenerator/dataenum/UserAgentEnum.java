package com.essar.jsongenerator.dataenum;

/***
 * User Agents describe the device accessing an application
 */
public enum UserAgentEnum {

	OCP_TestAutomation("OCP TestAutomation  "),
	EKS_TestAutomation("EKS TestAutomation  "),
	ENV_User(System.getenv("USERNAME").toUpperCase() +"  ");

	private String value;

	private UserAgentEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
