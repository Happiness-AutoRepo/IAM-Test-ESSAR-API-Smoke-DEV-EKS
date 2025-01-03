package com.essar.utils;

public enum TestCategory {
	 LOADBALANCER("Load Balancer(P+1)"), IVES("IVES"), IVESBOLA("IVES BOLA"), 
	 CONTAINER("Container"),MULTISIGNATURE("MultiSignature")
	 ,MULTISIGNATURESave("MultiSignature Save Form")
	 ,MULTISIGNATURERetrieve("MultiSignature Retrieve Form")
	 ,MULTISIGNATUREExtend("MultiSignature Extend Form");
	
	private String value;

	private TestCategory(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

