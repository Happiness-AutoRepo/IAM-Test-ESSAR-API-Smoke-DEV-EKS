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

@Description("SaveESignatureGetIntentLoadBalancerTests - Verify valid saveElectronicSignatureGenIntent test steps")
@Listeners(Listener.class)
public class SaveESignatureGetIntentLoadBalancer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureGetIntentLoadBalancerTests", "Verify response body and that service is returning 624 for successfull response", TestCategory.LOADBALANCER);
	}

	@Title("SaveESignatureGetIntentLoadBalancerTests - Verify that service is returning 624 for successfull response")
	@Test(priority = 0)
	public void saveESignatureGetIntentLoadBalancerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureGetIntentLoadBalancerStatusSuccess", "Verify that service is returning 624 for successfull response", TestCategory.LOADBALANCER);
		response = RestRequestUtils.saveESignatureGetIntentLoadBalancer(updateJSON(requestFilePath("intentByIdRequest")));
		
		assertEquals(retrieveStatusCode(response), "624");
	}

	@Title("SaveESignatureGetIntentLoadBalancerResponseBodySuccess - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "saveESignatureGetIntentLoadBalancerStatusSuccess")
	public void saveESignatureGetIntentLoadBalancerResponseBodySuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureGetIntentLoadBalancerResponseBodySuccess", "Verify that service is returning  expected response body", TestCategory.LOADBALANCER);
		
		verifyResponseBody(response, ResponseBodyType.INTENTBYID);
	}
}
