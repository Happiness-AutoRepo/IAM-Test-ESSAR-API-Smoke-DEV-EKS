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

@Description("IVESBOLA_GetESByIdTransType Tests - Verify valid IVES BOLA Test case")
@Listeners(Listener.class)
public class IVESBOLA_GetESByIdAppNameTests extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("IVESBOLA_GetESByIdAppNameTests", "Verify Return Code and Message for Non-Case Sensitive Upper&LowerCase AppName(WA-bolA-iv) for GetSignatureById", TestCategory.IVESBOLA);
	}

	@Title("IVESBOLA_GetESByIdAppNameTests")
	@Test(priority = 0)
	public void _1288753_1088394_TC1_6_Verify_Return_Code_and_Message_for_Non_Case_Sensitive_Upper_LowerCase_AppName_WA_bolA_iv_for_GetSignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("1288753: 1088394_TC1.6_", "Verify Return Code and Message for Non-Case Sensitive Upper&LowerCase AppName(WA-bolA-iv) for GetSignatureById", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "WA-bolA-iv");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
		
		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
	
	@Title("GeteSById IVES BOLA - Verify that service is returning  expected response body")
	@Test(priority = 1)
	public void _1289099_1088394_TC17_Verify_Return_Code_and_Message_for_Non_Case_Sensitive_Lowercase_AppName_wa_bola_iv_for_GetSignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("1288753: 1088394_TC1.6_", "Verify Return Code and Message for Non-Case Sensitive LowerCase AppName(WA-bolA-iv) for GetSignatureById", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "wa-bola-iv");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
		
		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
	
	@Title("GeteSById IVES BOLA - Verify that service is returning  expected response body")
	@Test(priority = 2)
	public void _1289119_1088394_TC1_8_Verify_Return_Code_and_Message_for_Non_Case_Sensitive_Uppercase_AppName_WA_BOLA_IV_for_GetSignatureById() throws IOException, JSONException, ParseException {
		
		childTestLogging("1288753: 1088394_TC1.6_", "Verify Return Code and Message for Non-Case Sensitive Upper AppName(WA-bolA-iv) for GetSignatureById", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "WA-BOLA-IV");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
		
		String expectedMessage = "Found an eSignature record.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
}