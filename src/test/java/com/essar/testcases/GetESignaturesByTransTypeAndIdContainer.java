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

@Description("GetESByTransTypeAndIdContainerServiceTests - Verify valid saveElectronicSignature test steps")
@Listeners(Listener.class)

public class GetESignaturesByTransTypeAndIdContainer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("GetESByTransType&IdContainerServiceTests", "Verify the response body and that the service is returning 604 for successfull response", TestCategory.CONTAINER);
	}

	@Title("GetESignaturesByTransTypeAndId - Verify that service is returning 604 for successfull response")
	@Test(priority = 0)
	public void geteSignatureByTransTypeAndIdContainerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("GetESByTransType&IdContainerServiceTests", "Verify that service is returning 604 for successfull response", TestCategory.CONTAINER);
		
		response = RestRequestUtils.getEsignaturesByTransTypeAndIdContainer(updateJSON(requestFilePath("byTransType&IdRequest")));
		
		assertEquals(retrieveStatusCode(response), "604");
	}

	@Title("GeteSByTransTypeAndIdContainerBodySuccess - Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "geteSignatureByTransTypeAndIdContainerStatusSuccess")
	public void geteSByTransTypeAndIdContainerResponseBodySuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("GeteSByTransTypeAndIdContainerResponseBodySuccess", "Verify that service is returning expected response body", TestCategory.CONTAINER);
		
		verifyResponseBody(response, ResponseBodyType.GETBYTTID);
	}
}
