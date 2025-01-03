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

@Description("SaveESignatureSaveIntentLoadBalancerTests - Verify valid saveElectronicSignatureSaveIntentStatement test steps")
@Listeners(Listener.class)

public class SaveESignatureSaveIntentLoadBalancer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureSaveIntentContainerTests", "Verify response body and that service is returning 225 for successfull response", TestCategory.LOADBALANCER);
	}

	@Title("SaveESignatureSaveIntentLoadBalancerTests- Verify that service is returning 225 for successfull response")
	@Test(priority = 0)
	public void saveESignatureSaveIntentLoadBalancerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureSaveIntentLoadBalancerStatusSuccess", "Verify that service is returning 225 for successfull response", TestCategory.LOADBALANCER);
		response = RestRequestUtils.saveESignatureSaveIntentLoadBalancer(updateJSON(requestFilePath("saveIntent")));

		assertEquals(retrieveStatusCode(response), "225");
	}

	@Title("SaveESignatureSaveIntentLoadBalancerResponseBodySuccess - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "saveESignatureSaveIntentLoadBalancerStatusSuccess")
	public void saveESignatureSaveIntentLoadBalancerResponseBodySuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureSaveIntentLoadBalancerResponseBodySuccess", "Verify that service is returning  expected response body", TestCategory.LOADBALANCER);
		
		verifyResponseBody(response, ResponseBodyType.SAVEINTENT);
	}
}
