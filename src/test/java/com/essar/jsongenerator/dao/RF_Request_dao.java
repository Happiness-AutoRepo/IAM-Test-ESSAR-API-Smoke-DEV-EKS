package com.essar.jsongenerator.dao;

public class RF_Request_dao {

	private String formId;
	private boolean includesSignatures;
	private boolean includesAttachments;
	
	public RF_Request_dao(SF_FormInfo_dao formDao){
		this.formId = formDao.getId();
		this.includesAttachments = true;
		this.includesSignatures = true;
	}
	
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public boolean isIncludesSignatures() {
		return includesSignatures;
	}
	public void setIncludesSignatures(boolean includesSignatures) {
		this.includesSignatures = includesSignatures;
	}
	public boolean isIncludesAttachments() {
		return includesAttachments;
	}
	public void setIncludesAttachments(boolean includesAttachments) {
		this.includesAttachments = includesAttachments;
	}
	
	
}
