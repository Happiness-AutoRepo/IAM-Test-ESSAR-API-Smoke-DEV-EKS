package com.essar.jsongenerator;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.essar.jsongenerator.dataenum.TokenValues;
import com.essar.utils.CommonUtils;
import com.aventstack.customreports.Status;
import com.essar.jsongenerator.dao.SF_dao;

public class RetrieveMultiSignatureForm extends CommonUtils {
	public String signatureForm;
	public SF_dao sfdao;

	public String makeRetrieveSignatureFormFull(String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating fully formed signature form for retrieve request");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);

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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
		request.put("includeSignatures", true);
		request.put("includeAttachments", true);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeRetrieveAttachmentsOnlyForm(String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating signature form for retrieve request - attachment only");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);
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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
		request.put("includeSignatures", false);
		request.put("includeAttachments", true);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeRetrieveSignatureOnlyForm(String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating signature form for retrieve request - signature only");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);

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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
		request.put("includeSignatures", true);
		request.put("includeAttachments", false);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeRetrieveSignatureDefaultForm(String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating signature form for retrieve request - default form");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);

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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
//		request.put("includeSignatures", true);
//		request.put("includeAttachments", false);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeRetrieveSignatureFalseForm(String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating signature form for retrieve request - false form");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);

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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
		request.put("includeSignatures", false);
		request.put("includeAttachments", false);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
	}

	public String makeRetrieveSignatureFormFullCustomValue(String customValue, String formId, String formHash, MultiSignatureForm MultiSignatureForm) throws JSONException {

		reporter.log(Status.INFO, "Generating signature form for retrieve request - full form, custom value");

		JSONObject rsfJSONObject = new JSONObject();
		rsfJSONObject = updateObjectToUseLinkHashMap(rsfJSONObject);

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
		rsfJSONObject.put("proof", proof);
		// Request
		JSONObject request = new JSONObject();
		request = updateObjectToUseLinkHashMap(request);
		request.put("formId", formId);
		request.put("includeSignatures", true);
		request.put("includeAttachments", true);
		rsfJSONObject.put("request", request);

		// Final Signature Form
		signatureForm = rsfJSONObject.toString(2);
		reporter.log(Status.INFO, "Generated signature form: " + signatureForm);
		return signatureForm;
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
}
