package com.essar.jsongenerator.dao;

import java.util.ArrayList;

import com.essar.jsongenerator.dataenum.AttachmentDocumentEnum;
import com.essar.utils.DataGenerator;

public class SF_Attachments_dao {

	private String id;
	private String type;
	private String storageLocation;
	private String storageId;
	private String name;
	private String version;
	private String data;
	private String hash;
	private ArrayList<String> correlatedIds;
	
	public SF_Attachments_dao(SF_FormInfo_dao formDao, SF_Signatures_dao signatureDao) {
		super();
		this.correlatedIds = new ArrayList();
		this.id = DataGenerator.generateRandomId(2);
		this.type = AttachmentDocumentEnum.Document.getValue();
		this.storageLocation = formDao.getStorageLocation();
		this.storageId = formDao.getStorageId();
		this.name = formDao.getName();
		this.version = formDao.getVersion();
		this.data = DataGenerator.generateRandomId(256);
		this.hash = formDao.getHash();
		this.correlatedIds.add(signatureDao.getId());
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getStorageId() {
		return storageId;
	}
	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public ArrayList<String> getCorrelatedIds() {
		return correlatedIds;
	}
	public void setCorrelatedIds(ArrayList<String> correlatedIds) {
		this.correlatedIds = correlatedIds;
	}
	//TODO Add ArrayList method to add corelatedIds
	
}
