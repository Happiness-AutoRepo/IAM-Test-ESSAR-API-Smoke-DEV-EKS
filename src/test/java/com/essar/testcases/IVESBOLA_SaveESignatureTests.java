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

@Description("IVES_SaveESignatureTests - Verify valid IVES BOLA Test case")
@Listeners(Listener.class)
public class IVESBOLA_SaveESignatureTests extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignature IVES BOLA appName", "eSign_Verify Return Code and Message for Non Case Sensitive Lower&Uppercase AppName", TestCategory.IVESBOLA);
	}

	@Title("SaveESignature IVES Bola appName - Verify that service is returning  expected response body")
	@Test(priority = 0)
	public void _1277651_1088394_TC1_1_eSign_Verify_Return_Code_and_Message_for_Non_Case_Sensitive_Upper_LowerCase_AppName_WA_bolA_iv_() throws IOException, JSONException, ParseException {
		
		childTestLogging("1277651: 1088394_TC1.1_", "eSign_Verify Return Code and Message for Non Case Sensitive Lower&Uppercase AppName(WA-bolA-iv)", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "WA-bolA-iv");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		assertEquals(retrieveStatusCode(response), "200");
		
		String expectedMessage = "The eSignature record has successfully saved.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
	
	@Title("SaveESignature IVES Bola appName - Verify that service is returning  expected response body")
	@Test(priority = 1)
	public void _1278047_1088394_TC1_2_eSign_Verify_Return_Code_and_Message_for_Lower_Case_AppName_wa_bola_iv_() throws IOException, JSONException, ParseException {
		
		childTestLogging("1278047: 1088394_TC1.2_", "eSign_Verify_Return Code and Message for Lower Case AppName (wa-bola-iv)", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "wa-bola-iv");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		assertEquals(retrieveStatusCode(response), "200");
		
		String expectedMessage = "The eSignature record has successfully saved.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}

	@Title("SaveESignature IVES Bola appName - Verify that service is returning  expected response body")
	@Test(priority = 2)
	public void _1280296_1088394_TC1_3_eSign_Verify_Return_Code_and_Message_for_Upper_Case_AppName_WA_BOLA_IV_() throws IOException, JSONException, ParseException {
		
		childTestLogging("1280296 : 1088394_TC1.3", "_eSign_Verify_Return Code and Message for Upper Case AppName(WA-BOLA-IV)", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "WA-BOLA-IV");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		assertEquals(retrieveStatusCode(response), "200");
		
		String expectedMessage = "The eSignature record has successfully saved.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
	
	@Title("SaveESignature IVES Bola appName - Verify that service is returning  expected response body")
	@Test(priority = 3)
	public void _1280376_1088394_TC1_4_eSign_Verify_Error_Code_and_Message_for_Invalid_AppName_WA_BOLA_1V_() throws IOException, JSONException, ParseException {
		
		childTestLogging("1280376: 1088394_TC1.4_", "eSign_Verify_Error Code and Message for Invalid AppName (WA-BOLA-1V)", TestCategory.IVESBOLA);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "WA-BOLA-1V");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		assertEquals(retrieveStatusCode(response), "510");
		
		String expectedMessage = "[\n{'510': 'The provided value for the appName field is invalid.'}\n]";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
}