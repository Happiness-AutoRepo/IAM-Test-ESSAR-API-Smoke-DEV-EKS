package com.essar.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.essar.annotations.Description;
import com.essar.annotations.Title;
import com.essar.utils.CommonUtils;
import com.essar.utils.Listener;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;

@Description("IVES_GetESByIdTransType Tests - Verify valid IVES Test case")
@Listeners(Listener.class)
public class IVES_GetESByIdTransTypeTests extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("IVES_GetESByIdTransType Tests", "eSign_GetById_Verify appName as IVES_OLA is not case sensitive with lowercase/uppercase characters", TestCategory.IVES);
	}

	@Title("GetESById IVES - eSign_GetById_Verify appName as IVES_OLA is not case sensitive with lowercase characters")
	@Test(priority = 0)
	public void _1183431_888938_TC_1_17_eSign_Verify_IVES_OLA_is_not_case_sensitive_using_lowercase_for_GetEsignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("1183211 : 888938 TC_1.11", "eSign_GetById_Verify appName as IVES_OLA is not case sensitive with lowercase characters", TestCategory.IVES);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "ives_ola");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");

		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}

	@Title("GeteSById IVES - Verify that service is returning  expected response body")
	@Test(priority = 1)
	public void _1183432_888938_TC_1_18_eSign_Verify_IVES_TRANSID_is_not_case_sensitive_using_uppercase_for_GetEsignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("1183432 : 888938 TC_1.18", "eSign_GetById_Verify appName as IVES_OLA is not case sensitive with uppercase characters", TestCategory.IVES);

		String json = updateJSON(requestFilePath("saveRequest"), "transType", "IVES_TRANSID");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
		
		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}

	@Title("GeteSById IVES - Verify that service is returning  expected response body")
	@Test(priority = 2)
	public void _363658_888938_TC_1_16_eSign_Verify_IVES_OLA_is_not_case_sensitive_using_upper_and_lowercase_for_GetEsignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("363658 : 888938 TC_1.16", "eSign_GetById_Verify appName as IVES_OLA is not case sensitive using both upper and lowercase characters",TestCategory.IVES);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "IVES_ola");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
		
		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
}