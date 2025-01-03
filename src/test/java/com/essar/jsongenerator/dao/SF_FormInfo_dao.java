package com.essar.jsongenerator.dao;

import com.essar.jsongenerator.dataenum.FormNameEnum;
import com.essar.utils.HashGenerator;
import com.essar.utils.DataGenerator;

public class SF_FormInfo_dao {

	private String businessId;
	private String businessInfo;
	private String id;
	private String storageLocation;
	private String storageId;
	private String name;
	private String version;
	private String hash;
	private boolean hashIncludesSignatures;

	public SF_FormInfo_dao() {
		super();
		this.businessId = DataGenerator.generateRandomId(10,24);
		this.businessInfo = DataGenerator.generateRandomId(200);
		this.id = DataGenerator.generateRandomId(2);
		this.storageLocation = DataGenerator.pickRandomValue(DataGenerator.storageLocation);
		this.storageId = DataGenerator.generateRandomId(24,24);
		this.name = com.essar.jsongenerator.dataenum.FormNameEnum._1040.getValue();
		this.version = DataGenerator.generateRandomId(10,24);
		setHash();
		this.hash = getHash();
		this.hashIncludesSignatures = false;
	}

	/***
	 * Using FormName Enumeration set construct form dao with form from
	 * FormNameEnum
	 * 
	 * @param FormNameEnum
	 */
	public SF_FormInfo_dao(FormNameEnum FormNameEnum) {
		super();
		this.businessId = DataGenerator.generateRandomId(10);
		this.businessInfo = DataGenerator.generateRandomSampleText(32);
		this.id = DataGenerator.generateRandomId(2);
		this.storageLocation = DataGenerator.pickRandomValue(DataGenerator.storageLocation);
		this.storageId = DataGenerator.generateRandomId(10,24);
		this.name = FormNameEnum.getValue();
		this.version = DataGenerator.generateRandomId(10,23);
		setHash();
		this.hash = HashGenerator.getHashofJsonSHA256(name + version);
		this.hashIncludesSignatures = false;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public void setBusinessIdLength(int length) {
		this.businessId = DataGenerator.generateRandomId(length);
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHash() {
		return hash;
	}

	/***
	 * Set hash from form name and version
	 */
	public void setHash() {
		hash = HashGenerator.getHashofJsonSHA256(name + version);
	}

	/***
	 * Set hash from a String value of your choice. Use this method to set the
	 * hash if your hash will include signatures
	 * 
	 * @param hashGenerated
	 */
	public void setHash(String hashGenerated) {
		hash = hashGenerated;
	}

	public boolean isHashIncludesSignatures() {
		return hashIncludesSignatures;
	}

	public void setHashIncludesSignatures(boolean hashIncludesSignatures) {
		this.hashIncludesSignatures = hashIncludesSignatures;
	}
	
}
