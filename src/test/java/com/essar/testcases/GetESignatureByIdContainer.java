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

@Description("GetESignatureByIdServiceTests - Verify valid saveElectronicSignature Tests with scenarios")
@Listeners(Listener.class)

public class GetESignatureByIdContainer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("GetESignatureByIdContainerStatusSuccess", "Verify that service is returning 603 for successfull response", TestCategory.CONTAINER);
	}

	@Title("GetESignatureByIdContainerServiceTest - Verify that service is returning 603 for successfull response")
	@Test(priority = 0)
	public void geteSignatureByIdContainerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("GetESignatureByIdContainerStatusSuccess", "Verify that service is returning 603 for successfull response", TestCategory.CONTAINER);
		
		String json = updateJSON(requestFilePath("saveRequest"), "appName", "TaxPro");
		response = RestRequestUtils.saveESignatureResponseContainer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdContainer(json2);

		assertEquals(retrieveStatusCode(response), "603");
	}

	@Title("GetESignatureByIdContainerResponseBodySuccess - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "geteSignatureByIdContainerStatusSuccess")
	public void getESignatureByIdContainerResponseBodySuccess() throws IOException, JSONException, ParseException {

		childTestLogging("GetESignatureByIdContainerResponseBodySuccess", "Verify that service is returning expected response message", TestCategory.CONTAINER);

		verifyResponseBody(response, ResponseBodyType.GET);
	}
}
