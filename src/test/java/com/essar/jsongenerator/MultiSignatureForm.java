package com.essar.jsongenerator;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aventstack.customreports.Status;
import com.essar.jsongenerator.dao.SF_FormInfo_dao;
import com.essar.jsongenerator.dataenum.TokenValues;
import com.essar.jsongenerator.dao.SF_dao;
import com.essar.jsongenerator.dataenum.AttachmentDocumentEnum;
import com.essar.utils.CommonUtils;
import com.essar.utils.DataGenerator;
import com.essar.utils.HashGenerator;

public class MultiSignatureForm extends CommonUtils {
	
	public String signatureForm;
	public SF_FormInfo_dao sfForm;
	public SF_dao sfdao;
	private List<String> coorelatedIdsArrayList = new ArrayList<String>();
	private HashMap<String, String> hashIdMap = new HashMap<String, String>();
	private String formIdForExtend;
	private String formHashForExtend;
	private Set<String> coorelatedIdSet;
	private int maxObjectLimit = 100;

	/**
	 * @param maxLimit
	 *            this is the maxlimit of the objects to add in addition to the
	 *            form object. Max count is 100 by design.
	 * @param signatureCount
	 *            this parameter is the amount of signatures to generate
	 * @param attachementCount
	 *            this parameter is the amount of attachments to generate counts
	 *            start from 0
	 * @param formIncudeSigs
	 *            this parameter is a boolean to set where the formHash includes
	 *            signatures or not
	 * @param includeFormHash
	 *            this parameter is a boolean to include the form in the hash
	 *            array
	 * @return returns fully formed signature form for multisignature save form
	 * @throws JSONException
	 */
	public  String makeSignatureForm(int maxLimit, int signatureCount, int attachementCount, boolean formIncudeSigs,
			boolean includeFormHash) throws JSONException {
		
		reporter.log(Status.INFO, "Generating fully formed signature form for save request");
		
		if (maxLimit < 101) {
			maxObjectLimit = maxLimit;
		}

		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject msfJSONObject = new JSONObject();
		msfJSONObject = updateObjectToUseLinkHashMap(msfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token
		
		// Form
		JSONObject form = new JSONObject();
		form = updateObjectToUseLinkHashMap(form);
		form.put("businessId", sfdao.getSfForm().getBusinessId());
		form.put("businessInfo", sfdao.getSfForm().getBusinessInfo());
		form.put("id", sfdao.getSfForm().getId());
		form.put("storageLocation", sfdao.getSfForm().getStorageLocation());
		form.put("storageId", sfdao.getSfForm().getStorageId());
		form.put("name", sfdao.getSfForm().getName());
		form.put("version", sfdao.getSfForm().getVersion());
		form.put("hash", sfdao.getSfForm().getHash());
		form.put("hashIncludesSignatures", formIncudeSigs);
		msfJSONObject.put("form", form);
		coorelatedIdsArrayList.add(sfdao.getSfForm().getId());
		hashIdMap.put(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		setFormIdAndFormHash(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(coorelatedIdsArrayList);

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			String distinctSigId = idChecker(sfdao.getSfSigns().getId());
			if (i == 97) {
				distinctSigId = "0";
			}
			signatures.put("id", distinctSigId);
			coorelatedIdSet.add(distinctSigId);
			signatures.put("userIdType", sfdao.getSfSigns().getUserIdType());
			signatures.put("userId", sfdao.getSfSigns().getUserId());
			signatures.put("name", sfdao.getSfSigns().getName());
			signatures.put("email", sfdao.getSfSigns().getEmail());
			signatures.put("sor", sfdao.getSfSigns().isSor());
			signatures.put("sorTimestamp", sfdao.getSfSigns().getSorTimeStamp());
			signatures.put("ipAddress", sfdao.getSfSigns().getIpAddress());
			// Change User Agent to reflect automation data
			signatures.put("userAgent", sfdao.getSfSigns().getUserAgent());
			signatures.put("sessionId", sfdao.getSfSigns().getSessionId());
			signatures.put("transType", sfdao.getSfSigns().getTransType());
			signatures.put("transId", sfdao.getSfSigns().getTransId());
			signatures.put("authType", sfdao.getSfSigns().getAuthType());
			signatures.put("authId", sfdao.getSfSigns().getAuthId());
			signatures.put("authLevel", sfdao.getSfSigns().getAuthLevel());
			signatures.put("businessName", sfdao.getSfSigns().getAppName());
			//intentIds array 
			intentIdsArray.put(sfdao.getSfSigns().getIntentIds());
			signatures.put("intentIds", intentIdsArray);
			signatures.put("position", sfdao.getSfSigns().getPosition());
			signaturesArray.put(signatures);

			// generate hash of signature
			hashIdMap.put(distinctSigId, HashGenerator.getHashofJsonSHA256(signatures.toString(2)));

			hashes = new JSONObject();
			hashes = updateObjectToUseLinkHashMap(hashes);
			hashes.put("type", "Signature");
			hashes.put("correlatedId", distinctSigId);
			hashes.put("value", hashIdMap.get(distinctSigId));
			hashesArray.put(hashes);
		}

		msfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "0";
			String distinctAttachId = attachId;
			if (coorelatedIdSet.size() < maxObjectLimit) {

				JSONArray attachCoIds = new JSONArray();

				if (attachementCount >= maxObjectLimit) {
					attachementCount = maxObjectLimit - coorelatedIdSet.size();
				}
				if (attachementCount < maxObjectLimit && attachementCount >= 1) {
					for (int i = 0; i < attachementCount; i++) {
					attachments = new JSONObject();
					attachments = updateObjectToUseLinkHashMap(attachments);

					while (isCorelatedIdPresent(attachId)) {
						distinctAttachId = idChecker(attachId);
						attachId = distinctAttachId;
						}

					attachments.put("id", attachId);
					sfdao.getSfAttachs().setType(AttachmentDocumentEnum.Image.getValue());
					attachments.put("type", sfdao.getSfAttachs().getType());
					attachments.put("storageLocation", sfdao.getSfAttachs().getStorageLocation());
					attachments.put("storageId", sfdao.getSfAttachs().getStorageId());
					attachments.put("name", sfdao.getSfAttachs().getName());
					attachments.put("version", sfdao.getSfAttachs().getVersion());
					attachments.put("data", sfdao.getSfAttachs().getData());
					attachments.put("hash", i + sfdao.getSfAttachs().getHash());
					attachCoIds = new JSONArray();
					int correalatedLimit = Integer.parseInt(DataGenerator.generateRandomId(1));
					if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
						Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
						correalatedLimit = idSet.size();
						}
						int k = 0;
						for (int j = 0; j < correalatedLimit; j++) {
							if (coorelatedIdsArrayList.get(k) == "0") {
								break;
							}
							attachCoIds.put(coorelatedIdsArrayList.get(k));
							k++;
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfForm.getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		msfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", sfForm.getId());
			hashes.put("value", sfForm.getHash());
			hashesArray.put(hashes);
		}
		msfJSONObject.put("hashes", hashesArray);

		// Final Signature Form
		signatureForm = msfJSONObject.toString(4);
		reporter.log(Status.INFO, "Generated signature: " + signatureForm);
		return signatureForm;
	}

	/**
	 * @param maxLimit
	 *            this is the maxlimit of the objects to add in addition to the
	 *            form object. Max count is 100 by design.
	 * @param signatureCount
	 *            this parameter is the amount of signatures to generate
	 * @param attachementCount
	 *            this parameter is the amount of attachments to generate counts
	 *            start from 0
	 * @param formIncudeSigs
	 *            this parameter is a boolean to set where the formHash includes
	 *            signatures or not
	 * @param includeFormHash
	 *            this parameter is a boolean to include the form in the hash
	 *            array
	 * @return returns fully formed signature form for multisignature save form
	 * @throws JSONException
	 */
	public  String makeSignatureFormOver100( int signatureCount, int attachementCount, boolean formIncudeSigs,
			boolean includeFormHash) throws JSONException {
		maxObjectLimit = 200;
		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject msfJSONObject = new JSONObject();
		msfJSONObject = updateObjectToUseLinkHashMap(msfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token
		
		// Form
		JSONObject form = new JSONObject();
		form = updateObjectToUseLinkHashMap(form);
		form.put("businessId", sfdao.getSfForm().getBusinessId());
		form.put("businessInfo", sfdao.getSfForm().getBusinessInfo());
		form.put("id", sfdao.getSfForm().getId());
		form.put("storageLocation", sfdao.getSfForm().getStorageLocation());
		form.put("storageId", sfdao.getSfForm().getStorageId());
		form.put("name", sfdao.getSfForm().getName());
		form.put("version", sfdao.getSfForm().getVersion());
		form.put("hash", sfdao.getSfForm().getHash());
		form.put("hashIncludesSignatures", formIncudeSigs);
		msfJSONObject.put("form", form);
		coorelatedIdsArrayList.add(sfdao.getSfForm().getId());
		hashIdMap.put(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		setFormIdAndFormHash(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(coorelatedIdsArrayList);

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			String distinctSigId = idCheckerOverLimit(sfdao.getSfSigns().getId());
			if (i == 97) {
				distinctSigId = "0";
			}
			signatures.put("id", distinctSigId);
			coorelatedIdSet.add(distinctSigId);
			signatures.put("userIdType", sfdao.getSfSigns().getUserIdType());
			signatures.put("userId", sfdao.getSfSigns().getUserId());
			signatures.put("name", sfdao.getSfSigns().getName());
			signatures.put("email", sfdao.getSfSigns().getEmail());
			signatures.put("sor", sfdao.getSfSigns().isSor());
			signatures.put("sorTimestamp", sfdao.getSfSigns().getSorTimeStamp());
			signatures.put("ipAddress", sfdao.getSfSigns().getIpAddress());
			// Change User Agent to reflect automation data
			signatures.put("userAgent", sfdao.getSfSigns().getUserAgent());
			signatures.put("sessionId", sfdao.getSfSigns().getSessionId());
			signatures.put("transType", sfdao.getSfSigns().getTransType());
			signatures.put("transId", sfdao.getSfSigns().getTransId());
			signatures.put("authType", sfdao.getSfSigns().getAuthType());
			signatures.put("authId", sfdao.getSfSigns().getAuthId());
			signatures.put("authLevel", sfdao.getSfSigns().getAuthLevel());
			signatures.put("businessName", sfdao.getSfSigns().getAppName());
			intentIdsArray.put(sfdao.getSfSigns().getIntentIds());
			signatures.put("intentIds", intentIdsArray);
			signatures.put("position", sfdao.getSfSigns().getPosition());
			signaturesArray.put(signatures);

			// generate hash of signature
			hashIdMap.put(distinctSigId, HashGenerator.getHashofJsonSHA256(signatures.toString(2)));

			hashes = new JSONObject();
			hashes = updateObjectToUseLinkHashMap(hashes);
			hashes.put("type", "Signature");
			hashes.put("correlatedId", distinctSigId);
			hashes.put("value", hashIdMap.get(distinctSigId));
			hashesArray.put(hashes);
		}

		msfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "0";
			String distinctAttachId = attachId;
			if (coorelatedIdSet.size() < maxObjectLimit) {

				JSONArray attachCoIds = new JSONArray();

				if (attachementCount >= maxObjectLimit) {
					attachementCount = maxObjectLimit - coorelatedIdSet.size();
				}
				if (attachementCount < maxObjectLimit && attachementCount >= 1) {
					for (int i = 0; i < attachementCount; i++) {
					attachments = new JSONObject();
					attachments = updateObjectToUseLinkHashMap(attachments);

					while (isCorelatedIdPresent(attachId)) {
						distinctAttachId = idCheckerOverLimit(attachId);
						attachId = distinctAttachId;
						}

					attachments.put("id", attachId);
					sfdao.getSfAttachs().setType(AttachmentDocumentEnum.Image.getValue());
					attachments.put("type", sfdao.getSfAttachs().getType());
					attachments.put("storageLocation", sfdao.getSfAttachs().getStorageLocation());
					attachments.put("storageId", sfdao.getSfAttachs().getStorageId());
					attachments.put("name", sfdao.getSfAttachs().getName());
					attachments.put("version", sfdao.getSfAttachs().getVersion());
					attachments.put("data", sfdao.getSfAttachs().getData());
					attachments.put("hash", i + sfdao.getSfAttachs().getHash());
					attachCoIds = new JSONArray();
					int correalatedLimit = Integer.parseInt(DataGenerator.generateRandomId(1));
					if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
						Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
						correalatedLimit = idSet.size();
						}
						int k = 0;
						for (int j = 0; j < correalatedLimit; j++) {
							if (coorelatedIdsArrayList.get(k) == "0") {
								break;
							}
							attachCoIds.put(coorelatedIdsArrayList.get(k));
							k++;
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfForm.getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		msfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", sfForm.getId());
			hashes.put("value", sfForm.getHash());
			hashesArray.put(hashes);
		}
		msfJSONObject.put("hashes", hashesArray);

		// Final Signature Form
		signatureForm = msfJSONObject.toString(4);
		return signatureForm;
	}
	
	public  String makeSignatureFormIncrementId(int maxLimit, int signatureCount, int attachementCount, boolean formIncudeSigs,
			boolean includeFormHash) throws JSONException {
		
		reporter.log(Status.INFO, "Generating fully formed signature form for save request");
		
		if (maxLimit < 101) {
			maxObjectLimit = maxLimit;
		}

		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject msfJSONObject = new JSONObject();
		msfJSONObject = updateObjectToUseLinkHashMap(msfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token
		
		// Form
		JSONObject form = new JSONObject();
		form = updateObjectToUseLinkHashMap(form);
		form.put("businessId", sfdao.getSfForm().getBusinessId());
		form.put("businessInfo", sfdao.getSfForm().getBusinessInfo());
		form.put("id", "0");
		form.put("storageLocation", sfdao.getSfForm().getStorageLocation());
		form.put("storageId", sfdao.getSfForm().getStorageId());
		form.put("name", sfdao.getSfForm().getName());
		form.put("version", sfdao.getSfForm().getVersion());
		form.put("hash", sfdao.getSfForm().getHash());
		form.put("hashIncludesSignatures", formIncudeSigs);
		msfJSONObject.put("form", form);
		coorelatedIdsArrayList.add(sfdao.getSfForm().getId());
		hashIdMap.put(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		setFormIdAndFormHash(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(coorelatedIdsArrayList);

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();
			int id = i +1;
			String distinctSigId = String.valueOf(id);
			signatures.put("id", distinctSigId);
			coorelatedIdSet.add(distinctSigId);
			signatures.put("userIdType", sfdao.getSfSigns().getUserIdType());
			signatures.put("userId", sfdao.getSfSigns().getUserId());
			signatures.put("name", sfdao.getSfSigns().getName());
			signatures.put("email", sfdao.getSfSigns().getEmail());
			signatures.put("sor", sfdao.getSfSigns().isSor());
			signatures.put("sorTimestamp", sfdao.getSfSigns().getSorTimeStamp());
			signatures.put("ipAddress", sfdao.getSfSigns().getIpAddress());
			// Change User Agent to reflect automation data
			signatures.put("userAgent", sfdao.getSfSigns().getUserAgent());
			signatures.put("sessionId", sfdao.getSfSigns().getSessionId());
			signatures.put("transType", sfdao.getSfSigns().getTransType());
			signatures.put("transId", sfdao.getSfSigns().getTransId());
			signatures.put("authType", sfdao.getSfSigns().getAuthType());
			signatures.put("authId", sfdao.getSfSigns().getAuthId());
			signatures.put("authLevel", sfdao.getSfSigns().getAuthLevel());
			signatures.put("businessName", sfdao.getSfSigns().getAppName());
			intentIdsArray.put(sfdao.getSfSigns().getIntentIds());
			signatures.put("intentIds", intentIdsArray);
			signatures.put("position", sfdao.getSfSigns().getPosition());
			signaturesArray.put(signatures);

			// generate hash of signature
			hashIdMap.put(distinctSigId, HashGenerator.getHashofJsonSHA256(signatures.toString(2)));

			hashes = new JSONObject();
			hashes = updateObjectToUseLinkHashMap(hashes);
			hashes.put("type", "Signature");
			hashes.put("correlatedId", distinctSigId);
			hashes.put("value", hashIdMap.get(distinctSigId));
			hashesArray.put(hashes);
		}

		msfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "0";
			String distinctAttachId = attachId;
			if (coorelatedIdSet.size() < maxObjectLimit) {

				JSONArray attachCoIds = new JSONArray();

				if (attachementCount >= maxObjectLimit) {
					attachementCount = maxObjectLimit - coorelatedIdSet.size();
				}
				if (attachementCount < maxObjectLimit && attachementCount >= 1) {
					for (int i = 0; i < attachementCount; i++) {
					attachments = new JSONObject();
					attachments = updateObjectToUseLinkHashMap(attachments);

					while (isCorelatedIdPresent(attachId)) {
						int attachmentId =signatureCount +1;
						distinctAttachId = String.valueOf(attachmentId);
						attachId = distinctAttachId;
						}

					attachments.put("id", attachId);
					sfdao.getSfAttachs().setType(AttachmentDocumentEnum.Image.getValue());
					attachments.put("type", sfdao.getSfAttachs().getType());
					attachments.put("storageLocation", sfdao.getSfAttachs().getStorageLocation());
					attachments.put("storageId", sfdao.getSfAttachs().getStorageId());
					attachments.put("name", sfdao.getSfAttachs().getName());
					attachments.put("version", sfdao.getSfAttachs().getVersion());
					attachments.put("data", sfdao.getSfAttachs().getData());
					attachments.put("hash", i + sfdao.getSfAttachs().getHash());
					attachCoIds = new JSONArray();
					int correalatedLimit = Integer.parseInt(DataGenerator.generateRandomId(1));
					if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
						Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
						correalatedLimit = idSet.size();
						}
						int k = 0;
						for (int j = 0; j < correalatedLimit; j++) {
							if (coorelatedIdsArrayList.get(k) == "0") {
								break;
							}
							attachCoIds.put(coorelatedIdsArrayList.get(k));
							k++;
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfForm.getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		msfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", sfForm.getId());
			hashes.put("value", sfForm.getHash());
			hashesArray.put(hashes);
		}
		msfJSONObject.put("hashes", hashesArray);

		// Final Signature Form
		signatureForm = msfJSONObject.toString(4);
		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		
		return signatureForm;
	}

	public  String makeSignatureFormCustomValue(String customValue,int maxLimit, int signatureCount, int attachementCount, boolean formIncudeSigs,
			boolean includeFormHash) throws JSONException {
		if (maxLimit < 101) {
			maxObjectLimit = maxLimit;
		}

		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject msfJSONObject = new JSONObject();
		msfJSONObject = updateObjectToUseLinkHashMap(msfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token
		
		// Form
		JSONObject form = new JSONObject();
		form = updateObjectToUseLinkHashMap(form);
		form.put("businessId", sfdao.getSfForm().getBusinessId());
		form.put("businessInfo", sfdao.getSfForm().getBusinessInfo());
		form.put("id", customValue);
		form.put("storageLocation", customValue);
		form.put("storageId", customValue);
		form.put("name", sfdao.getSfForm().getName());
		form.put("version", sfdao.getSfForm().getVersion());
		form.put("hash", sfdao.getSfForm().getHash());
		form.put("hashIncludesSignatures", formIncudeSigs);
		msfJSONObject.put("form", form);
		coorelatedIdsArrayList.add(sfdao.getSfForm().getId());
		hashIdMap.put(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		setFormIdAndFormHash(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(coorelatedIdsArrayList);

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();
			int id = i +1;
			String distinctSigId = String.valueOf(id);
			signatures.put("id", customValue);
			coorelatedIdSet.add(distinctSigId);
			signatures.put("userIdType", customValue);
			signatures.put("userId", customValue);
			signatures.put("name", customValue);
			signatures.put("email", customValue);
			signatures.put("sor", sfdao.getSfSigns().isSor());
			signatures.put("sorTimestamp", customValue);
			signatures.put("ipAddress", customValue);
			// Change User Agent to reflect automation data
			signatures.put("userAgent", sfdao.getSfSigns().getUserAgent());
			signatures.put("sessionId", customValue);
			signatures.put("transType", customValue);
			signatures.put("transId", customValue);
			signatures.put("authType", customValue);
			signatures.put("authId", customValue);
			signatures.put("authLevel", customValue);
			signatures.put("businessName", customValue);
			intentIdsArray.put(customValue);
			signatures.put("intentIds", intentIdsArray);
			signatures.put("position", sfdao.getSfSigns().getPosition());
			signaturesArray.put(signatures);

			// generate hash of signature
			hashIdMap.put(distinctSigId, HashGenerator.getHashofJsonSHA256(signatures.toString(2)));

			hashes = new JSONObject();
			hashes = updateObjectToUseLinkHashMap(hashes);
			hashes.put("type", customValue);
			hashes.put("correlatedId", customValue);
			hashes.put("value", hashIdMap.get(distinctSigId));
			hashesArray.put(hashes);
		}

		msfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "0";
			String distinctAttachId = attachId;
			if (coorelatedIdSet.size() < maxObjectLimit) {

				JSONArray attachCoIds = new JSONArray();

				if (attachementCount >= maxObjectLimit) {
					attachementCount = maxObjectLimit - coorelatedIdSet.size();
				}
				if (attachementCount < maxObjectLimit && attachementCount >= 1) {
					for (int i = 0; i < attachementCount; i++) {
					attachments = new JSONObject();
					attachments = updateObjectToUseLinkHashMap(attachments);

					while (isCorelatedIdPresent(attachId)) {
						int attachmentId =signatureCount +1;
						distinctAttachId = String.valueOf(attachmentId);
						attachId = distinctAttachId;
						}

					attachments.put("id", customValue);
					sfdao.getSfAttachs().setType(AttachmentDocumentEnum.Image.getValue());
					attachments.put("type", customValue);
					attachments.put("storageLocation", customValue);
					attachments.put("storageId", customValue);
					attachments.put("name", sfdao.getSfAttachs().getName());
					attachments.put("version", sfdao.getSfAttachs().getVersion());
					attachments.put("data", sfdao.getSfAttachs().getData());
					attachments.put("hash", i + sfdao.getSfAttachs().getHash());
					attachCoIds = new JSONArray();
					int correalatedLimit = Integer.parseInt(DataGenerator.generateRandomId(1));
					if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
						Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
						correalatedLimit = idSet.size();
						}
						int k = 0;
						for (int j = 0; j < correalatedLimit; j++) {
							if (coorelatedIdsArrayList.get(k) == "0") {
								break;
							}
							attachCoIds.put(customValue);
							k++;
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfForm.getHash());

						hashes = new JSONObject();
						hashes.put("type", customValue);
						hashes.put("correlatedId", customValue);
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		msfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", sfForm.getId());
			hashes.put("value", sfForm.getHash());
			hashesArray.put(hashes);
		}
		msfJSONObject.put("hashes", hashesArray);

		// Final Signature Form
		signatureForm = msfJSONObject.toString(4);
		return signatureForm;
	}
	
	private  void setFormIdAndFormHash(String formId, String formHash) {
		this.formIdForExtend = formId;
		this.formHashForExtend = formHash;
	}

	private  JSONObject updateObjectToUseLinkHashMap(JSONObject jsonObject) {
		jsonObject = new JSONObject();
		try {
			Field changeMap = jsonObject.getClass().getDeclaredField("nameValuePairs");
			changeMap.setAccessible(true);
			changeMap.set(jsonObject, new LinkedHashMap<>());
			changeMap.setAccessible(false);
		} catch (IllegalAccessException | NoSuchFieldException e) {

			e.printStackTrace();
		}
		return jsonObject;
	}

	private  boolean isCorelatedIdPresent(String id) {
		return coorelatedIdSet.contains(id);

	}

	private  String idChecker(String id) {
		if (coorelatedIdSet.contains(id)) {
			String genId = DataGenerator.generateRandomId(2);
			genId = idChecker(genId);
			id = genId;
			return id;
		}
		return id;
	}
	private  String idCheckerOverLimit(String id) {
		if (coorelatedIdSet.contains(id)) {
			String genId = DataGenerator.generateRandomId(3);
			genId = idCheckerOverLimit(genId);
			id = genId;
			return id;
		}
		return id;
	}
	public  String getFormIdForExtend() {
		return formIdForExtend;
	}

	public  String getFormHashForExtend() {
		return formHashForExtend;
	}

	public  String getNumberHash(int incrementer) {
		Long ins = Instant.now().toEpochMilli() + incrementer;
		String timeInSec = ins.toString();
		return timeInSec;
	}

	@SuppressWarnings("rawtypes")
	public  List getCorelatedIdList(){
		return coorelatedIdsArrayList;
	}
	
	public  Set<String> getCoorelatedIdSet() {
		return coorelatedIdSet;
	}

	public  void setCoorelatedIdSet(Set<String> coorelatedIdSet) {
		this.coorelatedIdSet = coorelatedIdSet;
	}
}