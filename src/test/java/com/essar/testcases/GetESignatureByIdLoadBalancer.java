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

@Description("GetESignatureByIdServiceTests-Verify valid saveElectronicSignature Tests with scenarios")
@Listeners(Listener.class)

public class GetESignatureByIdLoadBalancer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("GetESignatureByIdLoadBalancerStatusSuccess", "Verify that service is returning 603 for successfull response", TestCategory.LOADBALANCER);
	}

	@Title("GetESignatureByIdLoadBalancerServiceTest- Verify that service is returning 603 for successfull response")
	@Test(priority = 0)
	public void geteSignatureByIdLoadBalancerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("GetESignatureByIdLoadBalancerStatusSuccess", "Verify that service is returning 603 for successfull response", TestCategory.LOADBALANCER);

		String json = updateJSON(requestFilePath("saveRequest"), "appName", "TaxPro");
		response = RestRequestUtils.saveESignatureResponseLoadBalancer(json);
		
		String json2 = updateJSON(requestFilePath("byIdRequest"), "eSignatureId", retrieveElectronicSignatureID(response));
		response = RestRequestUtils.getESignatureByIdLoadBalancer(json2);
		
		assertEquals(retrieveStatusCode(response), "603");
	}

	@Title("GetESignatureByIdLoadBalancerResponseBodySuccess - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "geteSignatureByIdLoadBalancerStatusSuccess")
	public void getESignatureByIdLoadBalancerResponseBodySuccess() throws IOException, JSONException, ParseException {

		childTestLogging("GetESignatureByIdLoadBalancerResponseBodySuccess", "Verify that service is returning expected response body", TestCategory.LOADBALANCER);
		
		verifyResponseBody(response, ResponseBodyType.GET);
	}
}
