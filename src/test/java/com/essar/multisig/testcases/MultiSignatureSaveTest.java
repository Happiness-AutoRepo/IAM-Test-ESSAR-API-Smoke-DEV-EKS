package com.essar.multisig.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.essar.annotations.Description;
import com.essar.annotations.Title;
import com.essar.jsongenerator.MultiSignatureForm;
import com.essar.utils.CommonUtils;
import com.essar.utils.Listener;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;

@Description("SaveSignatureFormServiceTest - Verify valid saveSignatureForm MultiSignature Hash")
@Listeners(Listener.class)
public class MultiSignatureSaveTest extends CommonUtils {
	
	@BeforeClass
	public void init() {
		startLogging("MultiSignatureSaveTest", "Verify valid RetrieveSignatureForm MultiSignature", TestCategory.MULTISIGNATURESave);
	}
	   
	@Title("SaveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 0)
	public void saveMultiSignatureStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("saveMultiSignatureStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURESave);

		MultiSignatureForm msf = new MultiSignatureForm();
		
		String request = msf.makeSignatureForm(10, 1, 1, true, true);
//		String requ
		System.out.println(request);
		
		response = RestRequestUtils.saveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		
		assertEquals(statusCode, "2-2A");
	}	

	@Title("SaveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 1)
	public void saveMaxSigSaveMultiSignatureStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("saveMaxSigSaveMultiSignatureStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURESave);

//		MultiSignatureForm msf = new MultiSignatureForm();
//		
//		String request = msf.makeSignatureForm(100, 99, 0, true, true);
		
		String request = "{\r\n"
				+ "  \"form\": {\r\n"
				+ "    \"businessId\": \"89065264004543562775365437012612339239140750178051820915884\",\r\n"
				+ "    \"businessInfo\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim.\",\r\n"
				+ "    \"id\": \"3\",\r\n"
				+ "    \"storageLocation\": \"TBD SL2\",\r\n"
				+ "    \"storageId\": \"7996526222988078289068220338753445111784040716829733877755336377105908\",\r\n"
				+ "    \"name\": \"2048\",\r\n"
				+ "    \"version\": \"322676515071\",\r\n"
				+ "    \"hash\": \"780906767676465085011213271314008715091314403\",\r\n"
				+ "    \"hashIncludesSignatures\": true\r\n"
				+ "  },\r\n"
				+ "  \"signatures\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": \"31\",\r\n"
				+ "      \"userIdType\": \"TIN\",\r\n"
				+ "      \"userId\": \"850807974476114377\",\r\n"
				+ "      \"name\": \"Trace Williams\",\r\n"
				+ "      \"email\": \"trace.williams@FakeDomain.com\",\r\n"
				+ "      \"sor\": true,\r\n"
				+ "      \"sorTimestamp\": \"2024-12-05T09:47:31.612Z\",\r\n"
				+ "      \"ipAddress\": \"4.38.164.68\",\r\n"
				+ "      \"userAgent\": \"TODO: User Agent Text\",\r\n"
				+ "      \"sessionId\": \"G6E229-5931-4567-3715-028H4C002U2G\",\r\n"
				+ "      \"authType\": \"SADI\",\r\n"
				+ "      \"authId\": \"334762366680144666\",\r\n"
				+ "      \"authLevel\": \"LOA3\",\r\n"
				+ "      \"businessName\": \"OLA\",\r\n"
				+ "      \"intentIds\": [\"1\"],\r\n"
				+ "      \"position\": \"35509170\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"attachments\": [],\r\n"
				+ "  \"hashes\": [\r\n"
				+ "    {\r\n"
				+ "      \"type\": \"Signature\",\r\n"
				+ "      \"correlatedId\": \"31\",\r\n"
				+ "      \"value\": \"692031933720479306053144031634936685283143\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "";
		
		System.out.println(request);
		
		response = RestRequestUtils.saveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		
		assertEquals(statusCode, "2-2A");
	}
	
	@Title("SaveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 2)
	public void saveMaxAttachSaveMultiSignatureStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("saveMaxAttachSaveMultiSignatureStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURESave);

		MultiSignatureForm msf = new MultiSignatureForm();
		
		String request = msf.makeSignatureForm(100, 1, 98, true, true);
		System.out.println(request);
		
		response = RestRequestUtils.saveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		
		assertEquals(statusCode, "2-2A");
	}
	
	@Title("SaveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 3)
	public void saveMaxMixSaveMultiSignatureStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("saveMaxMixSaveMultiSignatureStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURESave);

		MultiSignatureForm msf = new MultiSignatureForm();
		
		String request = msf.makeSignatureForm(100, 50, 49, true, true);
		System.out.println(request);
		
		response = RestRequestUtils.saveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		
		assertEquals(statusCode, "2-2A");
	}
}