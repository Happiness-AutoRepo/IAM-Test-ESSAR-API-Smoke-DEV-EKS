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

public class SaveESignatureLoadBalancer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureStatusSuccess", "Verify the response body and that service is returning 603 for successfull response", TestCategory.LOADBALANCER);
	}

	@Title("SaveElectronicSignature Service Test For Load balancer - Verify that service is returning 200 for successfull response")
	@Test(priority = 0)
	public void saveESignatureStatusSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureStatusSuccess", "Verify that service is returning 200 for successfull response",TestCategory.LOADBALANCER);
		response = RestRequestUtils.saveESignatureResponseLoadBalancer(updateJSON(requestFilePath("saveRequest")));

		assertEquals(retrieveStatusCode(response), "200");
	}

	@Title("SaveElectronicSignature Service Test For Load balancer - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "saveESignatureStatusSuccess")
	public void saveESignatureResponseBodySuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureResponseBodySuccess", "Verify that service is returning expected response body", TestCategory.LOADBALANCER);
		
		verifyResponseBody(response, ResponseBodyType.SAVE);
	}
}
