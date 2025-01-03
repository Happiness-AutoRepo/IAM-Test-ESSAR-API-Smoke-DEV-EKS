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
import com.essar.jsongenerator.ExtendMultiSignatureForm;
import com.essar.jsongenerator.MultiSignatureForm;
import com.essar.utils.CommonUtils;
import com.essar.utils.Listener;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;

@Description("RetrieveSignatureFormServiceTest - Verify valid RetrieveSignatureForm MultiSignature")
@Listeners(Listener.class)
public class MultiSignatureExtendTest extends CommonUtils {
	
	@BeforeClass
	public void init() {
		startLogging("MultiSignatureExtendTest", "Verify valid RetrieveSignatureForm MultiSignature", TestCategory.MULTISIGNATUREExtend);
	}
	
	@Title("extendSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 0)
	public void extendMultiSignatureFullStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("extendMultiSignatureFullStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATUREExtend);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();

		String saveRequest = msf.makeSignatureFormIncrementId(10, 1, 0,false, false);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = esf.makeExtendSignatureFormIncremental(formIdFromResponse, formHashFromResponse, 1, 1, false, msf);
		System.out.println(request);
		
		response = RestRequestUtils.extendMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2B");
		String expectedMessage = "2-2B: The signature form has successfully been extended.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("extendSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 1)
	public void extendMaxSignatureMultiSignatureFullStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("extendMaxSignatureMultiSignatureFullStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATUREExtend);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();

		String saveRequest = msf.makeSignatureFormIncrementId(10, 1, 0,false, false);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);

		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = esf.makeExtendSignatureFormIncremental(formIdFromResponse, formHashFromResponse, 98, 0, false, msf);
		System.out.println(request);
		
		response = RestRequestUtils.extendMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2B");
		String expectedMessage = "2-2B: The signature form has successfully been extended.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("extendSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 2)
	public void extendMaxAttachmentMultiSignatureFullStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("extendMaxAttachmentMultiSignatureFullStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATUREExtend);

		MultiSignatureForm msf = new MultiSignatureForm();
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();

		String saveRequest = msf.makeSignatureFormIncrementId(10, 1, 0,false, false);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);

		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = esf.makeExtendSignatureFormIncremental(formIdFromResponse, formHashFromResponse, 0, 97, false, msf);
		System.out.println(request);
		
		response = RestRequestUtils.extendMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2B");
		String expectedMessage = "2-2B: The signature form has successfully been extended.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("extendSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 3)
	public void extendMultiSignatureFullStatusContainerSuccessMixAmmountOfSignatureAndAttachments() throws IOException, JSONException, ParseException {
		
		childTestLogging("extendMultiSignatureFullStatusContainerSuccessMixAmmountOfSignatureAndAttachments", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATUREExtend);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();

		String saveRequest = msf.makeSignatureFormIncrementId(10, 1, 0,false, false);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);

		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = esf.makeExtendSignatureFormIncremental(formIdFromResponse, formHashFromResponse, 45, 45, false, msf);
		System.out.println(request);
		
		response = RestRequestUtils.extendMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2B");
		String expectedMessage = "2-2B: The signature form has successfully been extended.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("extendSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 4)
	public void extendOverMaxSignatureMultiSignatureFullStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("extendOverMaxSignatureMultiSignatureFullStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATUREExtend);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		ExtendMultiSignatureForm esf = new ExtendMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 0,false, false);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = esf.makeExtendSignatureFormOverMax(formIdFromResponse, formHashFromResponse, 100, 0, false, msf);
		System.out.println(request);
		
		response = RestRequestUtils.extendMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "8-3B");
		String expectedMessage = "8-3B: The maximum objects of a form is 100.";
		assertEquals(message, expectedMessage);
	}
}