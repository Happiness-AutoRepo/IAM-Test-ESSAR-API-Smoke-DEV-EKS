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
import com.essar.jsongenerator.dao.SF_dao;
import com.essar.jsongenerator.dataenum.AttachmentDocumentEnum;
import com.essar.jsongenerator.dataenum.TokenValues;
import com.essar.utils.CommonUtils;
import com.essar.utils.DataGenerator;
import com.essar.utils.HashGenerator;
import com.jayway.jsonpath.JsonPath;

public class ExtendMultiSignatureForm extends CommonUtils {

	public String signatureForm;
	public SF_FormInfo_dao sfForm;
	public SF_dao sfdao;
	private List<String> coorelatedIds = new ArrayList<String>();
	private List<String> coorelatedIdsArrayList = new ArrayList<String>();
	private List<String> coorelatedSigIdsArrayList = new ArrayList<String>();
	private HashMap<String, String> hashIdMap = new HashMap<String, String>();
	private String formIdForExtend;
	private String formHashForExtend;
	private Set<String> coorelatedIdSet;
	private String saveFormId;
	private String saveSigId;
	private int maxObjectLimit = 100;
	private int idIntValue = 0;

	public String makeExtendSignatureForm(String formId, String formHash, int signatureCount, int attachementCount, boolean includeFormHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for extend request");

		coorelatedIds = new ArrayList<String>();
		sfdao = new SF_dao();

		JSONObject esfJSONObject = new JSONObject();
		esfJSONObject = updateObjectToUseLinkHashMap(esfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		// Token

		// Proof
		JSONObject proof = new JSONObject();
		proof.put("formId", formId);
		proof.put("formHash", formHash);
		proof.put("businessName", MultiSignatureForm.sfdao.getSfSigns().getAppName());
		proof.put("userId", MultiSignatureForm.sfdao.getSfSigns().getUserId());
		proof.put("sessionId", MultiSignatureForm.sfdao.getSfSigns().getSessionId());
		proof.put("ipAddress", MultiSignatureForm.sfdao.getSfSigns().getIpAddress());
		esfJSONObject.put("proof", proof);
		// Form
		JSONObject form = new JSONObject();
		form.put("businessId", sfdao.getSfForm().getBusinessId());
		form.put("businessInfo", sfdao.getSfForm().getBusinessInfo());
		form.put("id", sfdao.getSfForm().getId());
		form.put("storageLocation", sfdao.getSfForm().getStorageLocation());
		form.put("storageId", sfdao.getSfForm().getStorageId());
		form.put("name", sfdao.getSfForm().getName());
		form.put("version", sfdao.getSfForm().getVersion());
		form.put("hash", sfdao.getSfForm().getHash());
		form.put("hashIncludesSignatures", sfdao.getSfForm().isHashIncludesSignatures());
		// esfJSONObject.put("form", form);
		coorelatedIds.add(sfdao.getSfForm().getId());
		hashIdMap.put(sfdao.getSfForm().getId(), sfdao.getSfForm().getHash());
		sfForm = sfdao.getSfForm();

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			JSONArray intentIdsArray = new JSONArray();

			// idcheck

			if (coorelatedIds.contains(sfdao.getSfSigns().getId())
					|| coorelatedIds.contains(MultiSignatureForm.sfdao.getCoorelatedIds())) {
				sfdao.getSfSigns().setId(DataGenerator.generateRandomId(2));
			}
			signatures.put("id", sfdao.getSfSigns().getId());

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
			// intentArray
			intentIdsArray.put(sfdao.getSfSigns().getIntentIds());
			signatures.put("intentIds", intentIdsArray);
			signatures.put("position", sfdao.getSfSigns().getPosition());
			signaturesArray.put(signatures);
			coorelatedIds.add(sfdao.getSfSigns().getId());
			// generate hash of signature
			hashIdMap.put(sfdao.getSfSigns().getId(), HashGenerator.getHashofJsonSHA256(signatures.toString(4)));

			hashes = new JSONObject();
			hashes.put("type", "Signature");
			hashes.put("correlatedId", sfdao.getSfSigns().getId());
			hashes.put("value", hashIdMap.get(sfdao.getSfSigns().getId()));
			hashesArray.put(hashes);
		}

		esfJSONObject.put("signatures", signaturesArray);
		// Attachments
		JSONObject attachments = new JSONObject();
		attachments.put("id", sfForm.getId());
		attachments.put("type", sfdao.getSfAttachs().getType());
		attachments.put("storageLocation", sfForm.getStorageLocation());
		attachments.put("storageId", sfForm.getStorageId());
		attachments.put("name", sfForm.getName());
		attachments.put("version", sfForm.getVersion());
		attachments.put("data", sfdao.getSfAttachs().getData());
		attachments.put("hash", sfForm.getHash());

		JSONArray attachCoIds = new JSONArray();

		attachCoIds.put(sfdao.getSfSigns().getId());

		attachments.put("correlatedIds", attachCoIds);
		// attachmentsArray.put(attachments);

		for (int i = 0; i < attachementCount; i++) {
			attachments = new JSONObject();
			attachments.put("id", sfdao.getSfAttachs().getId());
			sfdao.getSfAttachs().setType(AttachmentDocumentEnum.Image.getValue());
			attachments.put("type", sfdao.getSfAttachs().getType());
			attachments.put("storageLocation", sfdao.getSfAttachs().getStorageLocation());
			attachments.put("storageId", sfdao.getSfAttachs().getStorageId());
			attachments.put("name", sfdao.getSfAttachs().getName());
			attachments.put("version", sfdao.getSfAttachs().getVersion());
			attachments.put("data", sfdao.getSfAttachs().getData());
			attachments.put("hash", sfdao.getSfAttachs().getHash());

			attachCoIds = new JSONArray();

			attachCoIds.put(sfdao.getSfSigns().getId());

			attachments.put("correlatedIds", attachCoIds);
			attachmentsArray.put(attachments);
		}
		esfJSONObject.put("attachments", attachmentsArray);

		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Document");
			hashes.put("correlatedId", sfForm.getId());
			hashes.put("value", sfForm.getHash());
			hashesArray.put(hashes);
		}

		esfJSONObject.put("hashes", hashesArray);

		// Final Signature Form
		signatureForm = esfJSONObject.toString(2);
		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		return signatureForm;
	}

	/**
	 * @param maxLimit         this is the maxlimit of the objects to add in
	 *                         addition to the form object. Max count is 100 by
	 *                         design.
	 * @param signatureCount   this parameter is the amount of signatures to
	 *                         generate
	 * @param attachementCount this parameter is the amount of attachments to
	 *                         generate counts start from 0
	 * @param formIncudeSigs   this parameter is a boolean to set where the formHash
	 *                         includes signatures or not
	 * @param includeFormHash  this parameter is a boolean to include the form in
	 *                         the hash array
	 * @return returns fully formed signature form for multisignature save form
	 * @throws JSONException
	 */
	public String makeExtendSignatureFormIncremental(String formId, String formHash, int signatureCount, int attachementCount, boolean includeFormHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for extend request");

		maxObjectLimit = 99;
		coorelatedSigIdsArrayList = new ArrayList<String>(MultiSignatureForm.getCoorelatedIdSet());
		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject esfJSONObject = new JSONObject();
		esfJSONObject = updateObjectToUseLinkHashMap(esfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token

		// Proof
		JSONObject proof = new JSONObject();
		proof = updateObjectToUseLinkHashMap(proof);
		proof.put("formId", formId);
		proof.put("formHash", formHash);
		proof.put("businessName", MultiSignatureForm.sfdao.getSfSigns().getAppName());
		proof.put("userId", MultiSignatureForm.sfdao.getSfSigns().getUserId());
		proof.put("sessionId", MultiSignatureForm.sfdao.getSfSigns().getSessionId());
		proof.put("ipAddress", MultiSignatureForm.sfdao.getSfSigns().getIpAddress());
		esfJSONObject.put("proof", proof);
		//
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(MultiSignatureForm.getCoorelatedIdSet());

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			int id = i + 2;
			if (id == 100) {
				break;
			}
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

		esfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);

		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "2";
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

						if (isCorelatedIdPresent(attachId)) {
							int attachmentId = signatureCount + 2 + i;
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
						int correalatedLimit = attachementCount;

						if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
							Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
							correalatedLimit = idSet.size();
						}
						int k = 0;
						// TODO add logic to check both coorelatedSigIdsArrayList and
						// coorelatedIdsArrayList

						for (int j = 0; j < correalatedLimit; j++) {

							if (coorelatedIdsArrayList.get(j) != "0" || coorelatedIdsArrayList.get(j) != "1"
									|| coorelatedIdsArrayList.get(j) != "2") {
								coorelatedSigIdsArrayList.contains(coorelatedIdsArrayList);

								attachCoIds.put("1");
								k++;
								break;
							} else if (coorelatedIdsArrayList.get(j) == "0" || coorelatedIdsArrayList.get(j) == "1"
									|| coorelatedIdsArrayList.get(j) == "2") {
								attachCoIds.put("1");
								break;
							} else {	
								attachCoIds.put("1");
								k++;
							}
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfdao.getSfAttachs().getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		esfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", formId);
			hashes.put("value", formHash);
			hashesArray.put(hashes);
		}
		esfJSONObject.put("hashes", hashesArray);
		// Final Signature Form
		signatureForm = esfJSONObject.toString(4);

		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		return signatureForm;
	}

	/**
	 * @param maxLimit         this is the maxlimit of the objects to add in
	 *                         addition to the form object. Max count is 100 by
	 *                         design.
	 * @param signatureCount   this parameter is the amount of signatures to
	 *                         generate
	 * @param attachementCount this parameter is the amount of attachments to
	 *                         generate counts start from 0
	 * @param formIncudeSigs   this parameter is a boolean to set where the formHash
	 *                         includes signatures or not
	 * @param includeFormHash  this parameter is a boolean to include the form in
	 *                         the hash array
	 * @return returns fully formed signature form for multisignature save form
	 * @throws JSONException
	 * 
	 *                       For mix amount work in progress to be refactored once
	 *                       done
	 */
	public String makeExtendSignatureFormIncremental2(String formId, String formHash, int signatureCount, int attachementCount, boolean includeFormHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for extend request");

		maxObjectLimit = 99;
		coorelatedSigIdsArrayList = new ArrayList<String>(MultiSignatureForm.getCoorelatedIdSet());
		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject esfJSONObject = new JSONObject();
		esfJSONObject = updateObjectToUseLinkHashMap(esfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token

		// Proof
		JSONObject proof = new JSONObject();
		proof = updateObjectToUseLinkHashMap(proof);
		proof.put("formId", formId);
		proof.put("formHash", formHash);
		proof.put("businessName", MultiSignatureForm.sfdao.getSfSigns().getAppName());
		proof.put("userId", MultiSignatureForm.sfdao.getSfSigns().getUserId());
		proof.put("sessionId", MultiSignatureForm.sfdao.getSfSigns().getSessionId());
		proof.put("ipAddress", MultiSignatureForm.sfdao.getSfSigns().getIpAddress());
		esfJSONObject.put("proof", proof);
		//
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(MultiSignatureForm.getCoorelatedIdSet());

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			int id = i + 2;
			if (id == 100) {
				break;
			}
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

		esfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);

		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "2";
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

						if (isCorelatedIdPresent(attachId)) {
							int attachmentId = signatureCount + 2 + i;
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
						int correalatedLimit = attachementCount;

						if (correalatedLimit > coorelatedIdsArrayList.size() || (correalatedLimit > signatureCount
								&& signatureCount > coorelatedIdsArrayList.size())) {
							Set<String> idSet = new HashSet<String>(coorelatedIdsArrayList);
							correalatedLimit = idSet.size();
						}
						int k = 0;
						// TODO add logic to check both coorelatedSigIdsArrayList and
						// coorelatedIdsArrayList

						for (int j = 0; j < correalatedLimit; j++) {

							if (coorelatedIdsArrayList.get(j) != "0" || coorelatedIdsArrayList.get(j) != "1"
									|| coorelatedIdsArrayList.get(j) != "2") {
								coorelatedSigIdsArrayList.contains(coorelatedIdsArrayList);

								attachCoIds.put(coorelatedSigIdsArrayList.get(0));
								k++;
								break;
							} else if (coorelatedIdsArrayList.get(j) == "0" || coorelatedIdsArrayList.get(j) == "1"
									|| coorelatedIdsArrayList.get(j) == "2") {
								break;
							} else {
								k++;
							}
						}
						attachments.put("correlatedIds", attachCoIds);
						attachId = attachments.getString("id");

						attachmentsArray.put(attachments);
						coorelatedIdSet.add(attachId);
						coorelatedIdsArrayList.add(attachId);
						hashIdMap.put(attachId, i + sfdao.getSfAttachs().getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		esfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", formId);
			hashes.put("value", formHash);
			hashesArray.put(hashes);
		}
		esfJSONObject.put("hashes", hashesArray);
		// Final Signature Form
		signatureForm = esfJSONObject.toString(4);
		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeExtendSignatureFormOverMax(String formId, String formHash, int signatureCount, int attachementCount, boolean includeFormHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for extend request");

		maxObjectLimit = 200;

		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject esfJSONObject = new JSONObject();
		esfJSONObject = updateObjectToUseLinkHashMap(esfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token

		// Proof
		JSONObject proof = new JSONObject();
		proof = updateObjectToUseLinkHashMap(proof);
		proof.put("formId", formId);
		proof.put("formHash", formHash);
		proof.put("businessName", MultiSignatureForm.sfdao.getSfSigns().getAppName());
		proof.put("userId", MultiSignatureForm.sfdao.getSfSigns().getUserId());
		proof.put("sessionId", MultiSignatureForm.sfdao.getSfSigns().getSessionId());
		proof.put("ipAddress", MultiSignatureForm.sfdao.getSfSigns().getIpAddress());
		esfJSONObject.put("proof", proof);
		//
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(MultiSignatureForm.getCoorelatedIdSet());

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			int id = i;
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

		esfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "2";
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

						if (isCorelatedIdPresent(attachId)) {
							int attachmentId = signatureCount + 2;
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
						hashIdMap.put(attachId, i + sfdao.getSfAttachs().getHash());

						hashes = new JSONObject();
						hashes.put("type", attachments.getString("type"));
						hashes.put("correlatedId", attachments.getString("id"));
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		esfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", formId);
			hashes.put("value", formHash);
			hashesArray.put(hashes);
		}
		esfJSONObject.put("hashes", hashesArray);
		// Final Signature Form
		signatureForm = esfJSONObject.toString(4);
		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeExtendSignatureFormCustomValue(String customValue, String formId, String formHash, int signatureCount, int attachementCount, boolean includeFormHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for extend request");

		maxObjectLimit = 200;

		coorelatedIdsArrayList = new ArrayList<String>();
		sfdao = new SF_dao();
		JSONObject esfJSONObject = new JSONObject();
		esfJSONObject = updateObjectToUseLinkHashMap(esfJSONObject);
		JSONArray signaturesArray = new JSONArray();
		JSONArray attachmentsArray = new JSONArray();
		JSONArray hashesArray = new JSONArray();
		JSONObject hashes = new JSONObject();
		hashes = updateObjectToUseLinkHashMap(hashes);
		// Token

		// Proof
		JSONObject proof = new JSONObject();
		proof = updateObjectToUseLinkHashMap(proof);
		proof.put("formId", formId);
		proof.put("formHash", formHash);
		proof.put("businessName", customValue);
		proof.put("userId", customValue);
		proof.put("sessionId", customValue);
		proof.put("ipAddress", customValue);
		esfJSONObject.put("proof", proof);
		//
		sfForm = sfdao.getSfForm();
		coorelatedIdSet = new HashSet<String>(MultiSignatureForm.getCoorelatedIdSet());

		// Signatures

		for (int i = 0; i < signatureCount; i++) {
			sfdao = new SF_dao();
			JSONObject signatures = new JSONObject();
			signatures = updateObjectToUseLinkHashMap(signatures);
			JSONArray intentIdsArray = new JSONArray();

			int id = i;
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

		esfJSONObject.put("signatures", signaturesArray);
		coorelatedIdsArrayList = new ArrayList<String>(coorelatedIdSet);
		// Attachments

		JSONObject attachments = new JSONObject();
		attachments = updateObjectToUseLinkHashMap(attachments);
		if (attachementCount != 0) {
			String attachId = "2";
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

						if (isCorelatedIdPresent(attachId)) {
							int attachmentId = signatureCount + 2;
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
						hashIdMap.put(attachId, i + sfdao.getSfAttachs().getHash());

						hashes = new JSONObject();
						hashes.put("type", customValue);
						hashes.put("correlatedId", customValue);
						hashes.put("value", attachments.getString("hash"));
						hashesArray.put(hashes);

					}
				}
			}
		}
		esfJSONObject.put("attachments", attachmentsArray);
		// Hashes
		if (includeFormHash) {
			hashes = new JSONObject();
			hashes.put("type", "Form");
			hashes.put("correlatedId", formId);
			hashes.put("value", formHash);
			hashesArray.put(hashes);
		}
		esfJSONObject.put("hashes", hashesArray);
		// Final Signature Form
		signatureForm = esfJSONObject.toString(4);
		reporter.log(Status.INFO, "Signature form: " + signatureForm);
		return signatureForm;
	}

	private void setFormIdAndFormHash(String formId, String formHash) {
		this.formIdForExtend = formId;
		this.formHashForExtend = formHash;
	}

	private boolean isCorelatedIdPresent(String id) {
		return coorelatedIdSet.contains(id);

	}

	private String getformIdFromSignatureForm(MultiSignatureForm MultiSignatureForm) {
		saveFormId = JsonPath.read(MultiSignatureForm.signatureForm, "$.form.id").toString();
		return saveFormId;
	}

	private String getSigIdFromSignatureForm(MultiSignatureForm MultiSignatureForm, int location) {
		String sigId = JsonPath.read(MultiSignatureForm.signatureForm, "$.signatures[" + location + "].id").toString();
		return sigId;
	}

	private void setSigIdFromSignatureForm(String Id) {
		saveSigId = Id;
	}

	private String idCheckwithSaveForm(String id, MultiSignatureForm MultiSignatureForm) {
		boolean isformIdInSaveForm = id.equals(getformIdFromSignatureForm(MultiSignatureForm));
		boolean isSigIdInSaveForm = id.equals(getSigIdFromSignatureForm(MultiSignatureForm, 0));
		if (isformIdInSaveForm) {
			return idChecker(id);
		}
		if (isSigIdInSaveForm) {
			return idChecker(id);
		}

		return id;

	}

	private String idChecker(String id) {

		if (!isCorelatedIdPresent(id)) {
			String genId = String.valueOf(idIntValue);
			idIntValue++;
			return genId;
		} else if (isCorelatedIdPresent(id)) {
			id = DataGenerator.generateRandomId(2);
			id = idChecker(id);
			return id;
		} else {
			return String.valueOf(idIntValue);
		}
	}

	private JSONObject updateObjectToUseLinkHashMap(JSONObject jsonObject) {
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

	public String getFormIdForExtend() {
		return formIdForExtend;
	}

	public String getFormHashForExtend() {
		return formHashForExtend;
	}

	public String getNumberHash(int incrementer) {
		Long ins = Instant.now().toEpochMilli() + incrementer;
		String timeInSec = ins.toString();
		return timeInSec;
	}

	public List getCorelatedIdList() {
		return coorelatedIdsArrayList;
	}

	public Set<String> getCoorelatedIdSet() {
		return coorelatedIdSet;
	}

	public static void main(String[] args) throws JSONException {
		MultiSignatureForm msf = new MultiSignatureForm();
		String msfSave = msf.makeSignatureForm(10, 1, 1, false, false);
		// System.out.println(msfSave);
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();
		// msf.makeSignatureForm(1,1, 1,true, true);

		String esfJson2 = esf.makeExtendSignatureFormIncremental("1020", "formHash", 0, 99, true, msf);
		System.out.println(esfJson2);
		System.out.println(msf.getCoorelatedIdSet());
		System.out.println(esf.getCoorelatedIdSet().size());
	}
}