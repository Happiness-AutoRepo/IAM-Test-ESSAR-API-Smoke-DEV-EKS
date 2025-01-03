package com.essar.jsongenerator.dao;

import java.util.ArrayList;

public class SF_Hashes_dao {
	private String type;
	private ArrayList<String> correlatedIds;
	private String value;
	
	public SF_Hashes_dao(SF_FormInfo_dao formDao){
		this.correlatedIds = new ArrayList();
		this.type = "Document";
		this.value= formDao.getHash();
		this.correlatedIds.add(formDao.getId());
	}
	
	public SF_Hashes_dao(SF_Signatures_dao signaturesDao){
		this.correlatedIds = new ArrayList();
		this.type = "Signatures";
		this.value= signaturesDao.getHash();
		this.correlatedIds.add(signaturesDao.getId());
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getCorrelatedIds() {
		return correlatedIds;
	}
	public void setCorrelatedIds(ArrayList<String> correlatedIds) {
		this.correlatedIds = correlatedIds;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
