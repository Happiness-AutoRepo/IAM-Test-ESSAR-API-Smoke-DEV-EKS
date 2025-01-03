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
import com.essar.utils.ResponseBodyType;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;

@Description("SaveESignatureServiceTest-Verify valid saveElectronicSignature test steps")
@Listeners(Listener.class)

public class SaveESignatureContainer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveElectronicSignature Service Test For Container", "Verify that service is returning 200 for successfull response", TestCategory.CONTAINER);
	}

	@Title("SaveElectronicSignature Service Test For Container - Verify that service is returning 200 for successfull response")
	@Test(priority = 0)
	public void saveESignatureStatusSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureStatusSuccess", "Verify that service is returning 200 for successfull response", TestCategory.CONTAINER);
		
		response = RestRequestUtils.saveESignatureResponseContainer(updateJSON(requestFilePath("saveRequest")));
		
		assertEquals(retrieveStatusCode(response), "200");
	}

	@Title("SaveElectronicSignature Service Test For Load balancer - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "saveESignatureStatusSuccess")
	public void saveESignatureResponseBodySuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureResponseBodySuccess", "Verify that service is returning expected response body", TestCategory.CONTAINER);
		
		verifyResponseBody(response, ResponseBodyType.SAVE);
	}
}
