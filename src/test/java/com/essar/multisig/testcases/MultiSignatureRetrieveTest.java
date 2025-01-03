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
import com.essar.jsongenerator.RetrieveMultiSignatureForm;
import com.essar.utils.CommonUtils;
import com.essar.utils.Listener;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;

@Description("RetrieveSignatureFormServiceTest - Verify valid RetrieveSignatureForm MultiSignature")
@Listeners(Listener.class)
public class MultiSignatureRetrieveTest extends CommonUtils {
	
	@BeforeClass
	public void init() {
		startLogging("MultiSignatureRetrieveTest", "Verify valid RetrieveSignatureForm MultiSignature", TestCategory.MULTISIGNATURERetrieve);
	}

	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 0)
	public void retrieveMultiSignatureFullStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("retrieveMultiSignatureFullStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 1, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureFormFull(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 1)
	public void retrieveMultiSignatureSignatureOnlyStatusContainerSuccess() throws IOException, JSONException, ParseException {
		
		childTestLogging("retrieveMultiSignatureSignatuerOnlyStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 1, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureOnlyForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 2)
	public void retrieveMultiSignatureAttachmentOnlyStatusContainerSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("retrieveMultiSignatureAttachmentOnlyStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 1, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveAttachmentsOnlyForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 3)
	public void retrieveMultiSignatureDefaultStatusContainerSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("retrieveMultiSignatureDefaultStatusContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 1, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureDefaultForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 4)
	public void retrieveMultiSignatureFalseFalseStatusContainerSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("retrieveMultiSignatureFalseFormContainerSuccess", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(10, 1, 1, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureFalseForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 5)
	public void retrieveMultiSignatureFalseFalseStatusContainerSuccess99Signatures() throws IOException, JSONException, ParseException {
		
		childTestLogging("retrieveMultiSignatureFalseFalseStatusContainerSuccess99Signatures", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(100, 99, 0, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureFalseForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
	
	@Title("RetrieveSignatureFormServiceTest - Verify that service returns correct status on hash")
	@Test(priority = 6)
	public void retrieveMultiSignatureFalseFalseStatusContainerSuccess98Attachement() throws IOException, JSONException, ParseException {

		childTestLogging("retrieveMultiSignatureFalseFalseStatusContainerSuccess98Attachement", "Verify that service is returning 200 for successfull response", TestCategory.MULTISIGNATURERetrieve);
		
		MultiSignatureForm msf = new MultiSignatureForm();
		RetrieveMultiSignatureForm rsf = new RetrieveMultiSignatureForm();

		String saveRequest = msf.makeSignatureForm(100, 1, 98, true, true);
		String saveResponse = RestRequestUtils.saveMultiSignatureFormContainer(saveRequest);
		
		statusCode = retrieveStatusCodeMS(saveResponse, 0);
		assertEquals(statusCode, "2-2A");
		
		String formIdFromResponse = retrieveFormID(saveResponse);
		String formHashFromResponse = retrieveFormHash(saveResponse);
		
		String request = rsf.makeRetrieveSignatureFalseForm(formIdFromResponse, formHashFromResponse,msf);
		System.out.println(request);
		
		response = RestRequestUtils.retrieveMultiSignatureFormContainer(request);
		statusCode = retrieveStatusCodeMS(response, 0);
		String message = retrieveResponseMessageMS(response, 0);
		
		assertEquals(statusCode, "2-2C");
		String expectedMessage = "2-2C: The signature form has been found.";
		assertEquals(message, expectedMessage);
	}
}