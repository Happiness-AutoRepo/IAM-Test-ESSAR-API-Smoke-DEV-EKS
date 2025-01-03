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

@Description("IVES_SaveESignatureTests - Verify valid IVES Test case")
@Listeners(Listener.class)
public class IVES_SaveESignatureTests extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("eSign_SaveSignature_Verify IVES", "eSign_SaveSignature_Verify IVES_TRANSID and IVES_OLA is not case sensitive using lowercase character", TestCategory.IVES);
	}

	@Title("eSign_SaveSignature_Verify IVES_TRANSID is not case sensitive using lowercase character")
	@Test(priority = 0)
	public void _1183121_888938_TC1_1_eSign_SaveSignature_Verify_IVES_TRANSID_is_not_case_sensitive_using_lowercase_character() throws IOException, JSONException, ParseException {
		
		childTestLogging("1183121 : 888938 TC_1.1", "eSign_SaveSignature_Verify IVES_TRANSID is not case sensitive using lowercase character", TestCategory.IVES);

		String json = updateJSON(requestFilePath("saveRequest"), "transType", "ives_transid");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		assertEquals(retrieveStatusCode(response), "200");
		
		String expectedMessage = "The eSignature record has successfully saved.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}

	@Title("eSign_SaveSignature_Verify appName as IVES_OLA is not case sensitive with lowercase characters")
	@Test(priority = 1)
	public void _1183134_888938_TC1_6_eSign_SaveSignature_Verify_IVES_APPNAME_is_not_case_sensitive_using_lowercase_characters() throws IOException, JSONException, ParseException {
		
		childTestLogging("1183134 : 888938 TC_1.6", "eSign_SaveSignature_Verify appName as IVES_OLA is not case sensitive with lowercase characters", TestCategory.IVES);
		
		String json = updateJSON(requestFilePath("saveRequest"), "appName", "ives_ola");
		response = RestRequestUtils.saveESignatureResponseContainer(json);

		assertEquals(retrieveStatusCode(response), "200");
		
		String expectedMessage = "The eSignature record has successfully saved.";
		assertEquals(retrieveResponseMessage(response), expectedMessage);
	}
}