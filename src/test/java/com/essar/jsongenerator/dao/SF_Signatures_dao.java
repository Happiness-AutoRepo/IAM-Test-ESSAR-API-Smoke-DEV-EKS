package com.essar.jsongenerator.dao;

import com.essar.jsongenerator.dataenum.AppNameEnum;
import com.essar.jsongenerator.dataenum.AuthLevelEnum;
import com.essar.jsongenerator.dataenum.AuthTypeEnum;
import com.essar.jsongenerator.dataenum.UserAgentEnum;
import com.essar.utils.CommonUtils;
import com.essar.utils.DataGenerator;

public class SF_Signatures_dao {
	private String id;
	private String userIdType;
	private String userId;
	private String name;
	private String email;
	private boolean sor;
	private String sorTimeStamp;
	private String ipAddress;
	private String userAgent;
	private String sessionId;
	private String transType;
	private String transId;
	private String authType;
	private String authId;
	private String authLevel;
	private String appName;
	private String intentIds;
	private String position;
	private String hash;

	public SF_Signatures_dao() {
		super();
		String Name = DataGenerator.generateRandomName();
		String timeStamp = DataGenerator.getCurrentTimestamp();
		// add if/else for id when final data object is completed for id check
		this.id = DataGenerator.generateRandomId(2);
		this.userIdType = "TIN";
		this.userId = DataGenerator.generateRandomId(10, 10);
		this.name = Name;
		this.email = DataGenerator.generateRandomEmail(name);
		this.sor = true;
		this.sorTimeStamp = DataGenerator.getCurrentTimestamp();
		this.ipAddress = "192.255.208.255";
		this.userAgent = "SBX "+ UserAgentEnum.ENV_User.getValue() + timeStamp + "999";
		this.sessionId = DataGenerator.generateRandomId(null, true);
		//this.transType = "CAF_DLN";
		//this.transId = DataGenerator.generateRandomId(16, 16);
		this.authType = "SADI";
		this.authId = DataGenerator.generateRandomId(3, 12);
		this.authLevel = "LoA3";
		this.appName =  getRandomAppName();
		this.intentIds = "1";
		this.position = DataGenerator.generateRandomId(8, 8);

	}

	public SF_Signatures_dao(AppNameEnum AppNameEnum, UserAgentEnum UserAgentEnum, AuthTypeEnum AuthTypeEnum,
			AuthLevelEnum AuthLevelEnum, String IntentId) {
		super();
		String Name = DataGenerator.generateRandomName();
		String timeStamp = DataGenerator.getCurrentTimestampAndMinusDays(1);
		// add if/else for id when final data object is completed for id check
		this.id = DataGenerator.generateRandomId(2);
		this.userIdType = "TIN";
		this.userId = DataGenerator.generateRandomId(10, 10);
		this.name = Name;
		this.email = DataGenerator.generateRandomEmail(name);
		this.sor = true;
		this.sorTimeStamp = timeStamp;
		this.ipAddress = "192.255.208.255";
		this.userAgent = "Test Automation DEV" + UserAgentEnum.getValue() + timeStamp + " 999";
		this.sessionId = DataGenerator.generateRandomId(null, true);
		this.authType = AuthTypeEnum.getValue();
		this.authId = DataGenerator.generateRandomId(12, 12);
		this.authLevel = AuthLevelEnum.getValue();
		this.appName = AppNameEnum.getValue();
		this.intentIds = IntentId;
		this.position = DataGenerator.generateRandomId(8, 8);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSor() {
		return sor;
	}

	public void setSor(boolean sor) {
		this.sor = sor;
	}

	public String getSorTimeStamp() {
		return sorTimeStamp;
	}

	public void setSorTimeStamp(String sorTimeStamp) {
		this.sorTimeStamp = sorTimeStamp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public String getAppName() {
		return appName;
	}
	public String getRandomAppName() {
		AppNameEnum appName = CommonUtils.getRandomEnum(AppNameEnum.class);
		return appName.getValue();
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getIntentIds() {
		return intentIds;
	}

	public void setIntentId(String intentId) {
		this.intentIds = intentId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hashGenerated) {
		hash = hashGenerated;
	}

}
