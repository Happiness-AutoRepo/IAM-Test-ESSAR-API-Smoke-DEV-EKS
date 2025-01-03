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

@Description("SaveESignatureGetIntentContainerTests - Verify valid saveElectronicSignatureGenIntent test steps")
@Listeners(Listener.class)
public class SaveESignatureGetIntentContainer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureGetIntentContainerTests", "Verify response body and that service is returning 624 for successfull response", TestCategory.CONTAINER);
	}

	@Title("SaveESignatureGetIntentContainerTests- Verify that service is returning 624 for successfull response")
	@Test(priority = 0)
	public void saveESignatureGetIntentContainerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureGetIntentContainerStatusSuccess", "Verify that service is returning 624 for successfull response", TestCategory.CONTAINER);
		response = RestRequestUtils.saveESignatureGetIntentContainer(updateJSON(requestFilePath("intentByIdRequest")));

		assertEquals(retrieveStatusCode(response), "624");
	}

	@Title("SaveESignatureGetIntentContainerResponseBodySuccess - Verify that service is returning  expected response body")
	@Test(dependsOnMethods = "saveESignatureGetIntentContainerStatusSuccess")
	public void saveESignatureGetIntentContainerResponseBodySuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureGetIntentContainerResponseBodySuccess", "Verify that service is returning  expected response body", TestCategory.CONTAINER);

		verifyResponseBody(response, ResponseBodyType.INTENTBYID);
	}
}
