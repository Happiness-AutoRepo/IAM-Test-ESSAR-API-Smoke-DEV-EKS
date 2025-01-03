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

@Description("SaveESignatureSaveIntentContainerTests - Verify valid saveElectronicSignatureSaveIntentStatement test steps")
@Listeners(Listener.class)

public class SaveESignatureSaveIntentContainer extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureSaveIntentContainerTests", "Verify response body and that service is returning 225 for successfull response", TestCategory.CONTAINER);
	}

	@Title("SaveESignatureSaveIntentContainerTests - Verify that service is returning 225 for successfull response")
	@Test(priority = 0)
	public void saveESignatureSaveIntentContainerStatusSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureSaveIntentContainerStatusSuccess", "Verify that service is returning 225 for successfull response", TestCategory.CONTAINER);
		response = RestRequestUtils.saveESignatureSaveIntentContainer(updateJSON(requestFilePath("saveIntent")));

		assertEquals(retrieveStatusCode(response), "225");
	}

	@Title("SaveESignatureSaveIntentContainerResponseBodySuccess- Verify that service is returning  expected response body")
	@Test(priority = 1, dependsOnMethods = "saveESignatureSaveIntentContainerStatusSuccess")
	public void saveESignatureSaveIntentContainerResponseBodySuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("SaveESignatureSaveIntentContainerResponseBodySuccess", "Verify that service is returning  expected response body", TestCategory.CONTAINER);
		
		verifyResponseBody(response, ResponseBodyType.SAVEINTENT);
	}
}
