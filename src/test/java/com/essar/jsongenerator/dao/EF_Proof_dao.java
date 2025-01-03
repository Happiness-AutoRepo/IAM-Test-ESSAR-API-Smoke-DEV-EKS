package com.essar.jsongenerator.dao;

public class EF_Proof_dao {

	private String formId;
	private String formHash;
	
	public EF_Proof_dao(String formId, String formHash){
		this.formId = formId;
		this.formHash = formHash;
	}
	
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormHash() {
		return formHash;
	}
	public void setFormHash(String formHash) {
		this.formHash = formHash;
	}
	
	
}
