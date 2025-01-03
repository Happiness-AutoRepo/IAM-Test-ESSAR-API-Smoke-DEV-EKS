package com.essar.utils;

import static com.essar.utils.JsonModifier.removeFieldsFromJSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.skyscreamer.jsonassert.JSONAssert;

import com.aventstack.customreports.CustomReports;
import com.aventstack.customreports.CustomTest;
import com.aventstack.customreports.MediaEntityBuilder;
import com.aventstack.customreports.Status;
import com.essar.custom.report.utils.Driver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class CommonUtils {

	protected static String response;
	protected static String statusCode;
	protected static String eSigID;

	protected String responseData;
	protected String updatedResponse;
	protected String jsonRequest;

	protected static CustomReports report;
	protected static CustomTest test;
	private static CustomTest childTest;
	public static CustomTest reporter;

	public void startLogging(String testCaseName, String testCaseDescription, TestCategory testCategory) {

		test = report.createTest(testCaseName, testCaseDescription);
		test.assignCategory(testCategory.getValue());

		reporter = test;
	}

	public void childTestLogging(String testCaseName, String testCaseDescription, TestCategory testCategory) {
		childTest = test.createNode(testCaseName, testCaseDescription);
		childTest.assignCategory(testCategory.getValue());

		reporter = childTest;
		reporter.log(Status.INFO, "Test started");
	}

	/**
	 * @author 2r8sb
	 * @param myObject
	 * @return
	 * @description to update token value in json file
	 */
	@SuppressWarnings("unchecked")
	public static String updateTokenValue(String file) throws JSONException, IOException, ParseException {

		FileReader reader = new FileReader(file);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

		return jsonObject.toString();
	}

	public void logWithScreenCapture(Status status, String message) {

		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SS");

			// Main logic of logging mechanism: takes screenshot, names it using
			// date-String and logs the data with the screenshot attached
			String name = "" + formater.format(calendar.getTime()) + ".png";
			File srcImage = ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcImage, new File("./target/reports/images/" + name));

			test.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath("./images/" + name).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String convertJavaObjectToString(Object myObject) {
		String body = null;
		ObjectMapper obMap = new ObjectMapper();
		try {
			body = obMap.writerWithDefaultPrettyPrinter().writeValueAsString(myObject);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return body;
	}

	public static String readProperty(String fieldName)

			throws IOException

	{

		String envName = System.getProperty("env");

		Properties prop = new Properties();

		String value;

		if (envName.equals("aqt"))

		{

			prop.load(new FileInputStream(System.getProperty("user.dir")

					+ "//src//test//resources//application-aqt.properties"));

		}

		else if (envName.equals("eite"))

		{

			prop.load(new FileInputStream(System.getProperty("user.dir")

					+ "//src//test//resources//application-eite.properties"));

		}

		else if (envName.equals("prod"))

		{

			prop.load(new FileInputStream(System.getProperty("user.dir")

					+ "//src//test//resources//application-prod.properties"));

		}

		value = prop.getProperty(fieldName);

		if (value == null)

		{

			throw new RuntimeException(

					"Property does not exist, cannot take in an empty String or null value for the \"value\" constructor");

		}

		else

		{

			return value;

		}

	}

	public static List<String> expListFromArrayOfObjects(String path) throws IOException, JSONException {
		List<String> expectedList = new ArrayList<>();
		String fileName = "src/test/resources/response/" + path + ".json";
		String expectedJson = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
		JSONArray jsonExpected = new JSONArray(expectedJson);

		for (int i = 0; i < jsonExpected.length(); i++) {
			String valueExpected = jsonExpected.getString(i);
			expectedList.add(valueExpected);
		}

		return expectedList;
	}

	public String getPayload(String payload) throws IOException {
		String file = System.getProperty("user.dir") + "//src//test//resources//" + payload;
		System.out.println(file);
		String s = new String();
		StringBuffer sb = new StringBuffer();
		FileReader fr;
		fr = new FileReader(new File(file));
		BufferedReader br = new BufferedReader(fr);
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		br.close();
		// System.out.println(sb.toString());
		return sb.toString();
	}

	public static String xmlResponseReader(String fileName) {

		String path = "src/test/resources/response/";
		String xmlFile = path + fileName + ".xml";
		String readableXML = "";

		// LOGGER.debug("Loading XML file: " + xmlFile);

		try {
			readableXML = new String(Files.readAllBytes(Paths.get(xmlFile)), StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return readableXML;
	}

	public static String xmlRequestReader(String fileName) {

		String path = "src/test/resources/request/";
		String xmlFile = path + fileName + ".xml";
		String readableXML = "";

		// LOGGER.debug("Loading XML file: " + xmlFile);

		try {
			readableXML = new String(Files.readAllBytes(Paths.get(xmlFile)), StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return readableXML;
	}

	public static String jsonResponseReader(String fileName) {

		String path = "src/test/resources/response/";
		String jsonFile = path + fileName + ".json";
		String readableJSON = "";

		// LOGGER.info("Loading JSON file: " + jsonFile);

		try {
			readableJSON = new String(Files.readAllBytes(Paths.get(jsonFile)), StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return readableJSON;
	}

	public static String requestFilePath(String fileName) {

		String path = "src/test/resources/request/";
		String jsonFile = path + fileName + ".json";
		return jsonFile;
	}

	public static String jsonRequestReader(String fileName) {

		String path = "src/test/resources/request/";
		String jsonFile = path + fileName + ".json";
		String readableJSON = "";

		// LOGGER.info("Loading JSON file: " + jsonFile);

		try {
			readableJSON = new String(Files.readAllBytes(Paths.get(jsonFile)), StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return readableJSON;
	}

	@SuppressWarnings("unchecked")
	public static String updateJsonValue(String file, String field, String value) throws JSONException, IOException, ParseException {
		FileReader reader = new FileReader(file);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

		jsonObject.remove(field);
		jsonObject.put(field, value);
		return jsonObject.toString();
	}

	/**
	 * This method updates given field with the new value
	 * 
	 * @param file
	 * @param field
	 * @param value
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static String updateJson(String file, String field, String value) throws JSONException, IOException, ParseException {

		reporter.log(Status.INFO, "Updating JSON field " + field + " with the value of " + value);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
		jsonObject.remove(field);
		jsonObject.put(field, value);
		org.json.JSONObject jsonString = new org.json.JSONObject(jsonObject);

		reporter.log(Status.INFO, "Resulting JSON: " + jsonString.toString(4));
		return jsonString.toString(4);
	}

	@SuppressWarnings("unchecked")
	public static String updateJsonRemoveField(String file, String field) throws JSONException, IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
		jsonObject.remove(field);
		org.json.JSONObject jsonString = new org.json.JSONObject(jsonObject);
		return jsonString.toString(4);
	}

	@SuppressWarnings("unchecked")
	public static String updateJsonAddEmptyJsonArrayObject(String file, String jsonArrayFieldName) throws JSONException, IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
		JSONArray emptyJsonArrayObject = new JSONArray();
		jsonObject.put(jsonArrayFieldName, emptyJsonArrayObject);
		org.json.JSONObject jsonString = new org.json.JSONObject(jsonObject);
		return jsonString.toString(4);
	}

	/***
	 * update this doc
	 * 
	 * @param file               This is the json request to be updated
	 * @param requestAttribute   Attribute under Request is either AccessSubject or
	 *                           Resource
	 * @param valueLocation      This is the integer location of the value in the
	 *                           json array
	 * @param AttributeIdOrValue This is the attribute or Value MapName
	 * @param value              This is the Key value to be updated to
	 * @return returns back the updated request json
	 * @throws JSONException
	 * @see
	 * 
	 */
	public String updateJsonRequest(String request, String requestAttribute, String AttributeIdOrValue, Object value) throws JSONException {

		reporter.log(Status.INFO, "Updating JSON request with requestAttribute and AttributeIdOrValue");
		jsonRequest = JsonPath.parse(request).set("$." + requestAttribute + "."
				+ AttributeIdOrValue, value).jsonString();
		org.json.JSONObject jsonObject = new org.json.JSONObject(jsonRequest);
		jsonRequest = jsonObject.toString(4);

		reporter.log(Status.INFO, "Updated JSON request: " + jsonRequest);
		return jsonRequest;
	}

	/***
	 * This method is used to update a json array object attribute at a specified
	 * index to a singular value within the array object
	 * 
	 * @param file               This is the json request to be updated
	 * @param requestAttribute   Attribute under Request is either AccessSubject or
	 *                           Resource
	 * @param valueLocation      This is the integer location of the value in the
	 *                           json array
	 * @param AttributeIdOrValue This is the attribute or Value MapName
	 * @param value              This is the Key value to be updated to
	 * @return returns back the updated request json
	 * @throws JSONException
	 * @see
	 * 
	 */
	public String updateJsonArrayRequest(String request, String requestAttribute, String AttributeLocation, String AttributeIdOrValue, Object value) throws JSONException {
		jsonRequest = JsonPath.parse(request).set("$." + requestAttribute + ".[" + AttributeLocation + "]"
				+ AttributeIdOrValue, value).jsonString();

		org.json.JSONObject jsonObject = null;
		jsonObject = updateObjectToUseLinkHashMap(jsonObject);
		jsonObject = new org.json.JSONObject(jsonRequest);
		jsonRequest = jsonObject.toString(4);
		return jsonRequest;

	}

	/***
	 * This method is used to update a json array object at a specified index to a
	 * singular value This method will update whole index to specified value
	 * 
	 * @param file               This is the json request to be updated
	 * @param requestAttribute   Attribute under Request is either AccessSubject or
	 *                           Resource
	 * @param valueLocation      This is the integer location of the value in the
	 *                           json array
	 * @param AttributeIdOrValue This is the attribute or Value MapName
	 * @param value              This is the Key value to be updated to
	 * @return returns back the updated request json
	 * @throws JSONException
	 * @see
	 * 
	 */
	public String updateJsonArrayObjectRequest(String request, String requestAttribute, String AttributeLocation, Object value) throws JSONException {

		jsonRequest = JsonPath.parse(request).set("$." + requestAttribute + ".[" + AttributeLocation
				+ "]", value).jsonString();

		org.json.JSONObject jsonObject = null;
		jsonObject = updateObjectToUseLinkHashMap(jsonObject);
		jsonObject = new org.json.JSONObject(jsonRequest);
		jsonRequest = jsonObject.toString(4);
		return jsonRequest;

	}

	/***
	 * This method is used to update a json array object at a specified index to a
	 * singular value This method will update whole index to specified value
	 * 
	 * @param file               This is the json request to be updated
	 * @param requestAttribute   Attribute under Request is either AccessSubject or
	 *                           Resource
	 * @param valueLocation      This is the integer location of the value in the
	 *                           json array
	 * @param AttributeIdOrValue This is the attribute or Value MapName
	 * @param value              This is the Key value to be updated to
	 * @return returns back the updated request json
	 * @throws JSONException
	 * @see
	 * 
	 */
	public String updateJsonAddEmptyArrayObject(String request, String requestAttribute) throws JSONException {

		reporter.log(Status.INFO, "Setting request attribute '" + requestAttribute + "' to an empty array");
		String[] emptyArray = new String[] {};
		jsonRequest = JsonPath.parse(request).set("$." + requestAttribute, emptyArray).jsonString();

		org.json.JSONObject jsonObject = null;
		jsonObject = updateObjectToUseLinkHashMap(jsonObject);
		jsonObject = new org.json.JSONObject(jsonRequest);
		jsonRequest = jsonObject.toString(4);
		reporter.log(Status.INFO, "Resulting JSON: " + jsonRequest.toString());
		return jsonRequest;
	}

	/***
	 * This method is used to add a json array object at a specified index to a
	 * singular value This method will update whole index to specified value
	 * 
	 * @param file               This is the json request to be updated
	 * @param requestAttribute   Attribute under Request is either AccessSubject or
	 *                           Resource
	 * @param valueLocation      This is the integer location of the value in the
	 *                           json array
	 * @param AttributeIdOrValue This is the attribute or Value MapName
	 * @param value              This is the Key value to be updated to
	 * @return returns back the updated request json
	 * @throws JSONException
	 * @see
	 * 
	 */
	public String addJsonArrayObjectRequest(String request, String requestAttribute, String AttributeLocation, Object value) throws JSONException {

		jsonRequest = JsonPath.parse(request).add("$." + requestAttribute + ".[" + AttributeLocation
				+ "]", value).jsonString();

		org.json.JSONObject jsonObject = null;
		jsonObject = updateObjectToUseLinkHashMap(jsonObject);
		jsonObject = new org.json.JSONObject(jsonRequest);
		jsonRequest = jsonObject.toString(4);
		return jsonRequest;

	}

	private org.json.JSONObject updateObjectToUseLinkHashMap(org.json.JSONObject jsonObject) {
		jsonObject = new org.json.JSONObject();
		try {
			Field changeMap = jsonObject.getClass().getDeclaredField("nameValuePairs");
			changeMap.setAccessible(true);
			changeMap.set(jsonObject, new LinkedHashMap<>());
			changeMap.setAccessible(false);
		} catch (IllegalAccessException | NoSuchFieldException e) {

			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 
	 * @param file
	 * @param field
	 * @param value
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * 
	 * @description This method is used to update token value and updates provided
	 *              field in the JSON file
	 */
	@SuppressWarnings("unchecked")
	public static String updateJSON(String file, String field, String value) throws JSONException, IOException, ParseException {

		FileReader reader = new FileReader(file);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		jsonObject.remove(field);
		jsonObject.put(field, value);

		String json = jsonObject.toString();
		reporter.log(Status.INFO, "Request JSON: " + json);
		return json;
	}

	/**
	 * @author 2r8sb
	 * @param myObject
	 * @return
	 * 
	 * @description This method is used to update token value in JSON file
	 */
	@SuppressWarnings("unchecked")
	public static String updateJSON(String file) throws JSONException, IOException, ParseException {

		FileReader reader = new FileReader(file);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

		String json = jsonObject.toString();
		reporter.log(Status.INFO, "Request JSON: " + json);
		return json;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts electronic signature ID from the provided
	 *              JSON
	 */
	public String retrieveElectronicSignatureID(String JSON) {
		reporter.log(Status.INFO, "Retrieving electronicSignatureId...");
		eSigID = JsonPath.read(JSON, "$.item.electronicSignatureId").toString();
		reporter.log(Status.INFO, "electronicSignatureId: " + eSigID);
		return eSigID;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts status code from the provided JSON
	 */
	public String retrieveStatusCode(String JSON) {
		reporter.log(Status.INFO, "Retrieving status code...");
		statusCode = JsonPath.read(JSON, "$.returnCode").toString();
		reporter.log(Status.INFO, "Status code: " + statusCode);
		return statusCode;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts multisignature status code from the
	 *              provided JSON
	 */
	public String retrieveStatusCodeMS(String JSON, int itemNumber) {
		reporter.log(Status.INFO, "Retrieving status code for item#" + itemNumber);
		statusCode = JsonPath.read(JSON, "$.codes[" + itemNumber + "]").toString();
		reporter.log(Status.INFO, "Status code: " + statusCode);
		return statusCode;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts response message from the provided JSON
	 */
	public String retrieveResponseMessage(String JSON) {
		reporter.log(Status.INFO, "Retrieving response message...");
		String message = JsonPath.read(JSON, "$.message").toString().trim();
		reporter.log(Status.INFO, "Response message: " + message);
		return message;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts multisignature response message from the
	 *              provided JSON
	 */
	public String retrieveResponseMessageMS(String JSON, int itemNumber) {
		reporter.log(Status.INFO, "Retrieving response message for item#" + itemNumber);
		String message = JsonPath.read(response, "$.messages.[" + itemNumber + "]").toString().trim();
		reporter.log(Status.INFO, "Response message: " + message);
		return message;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts formId from the provided JSON
	 */
	public String retrieveFormID(String JSON) {
		reporter.log(Status.INFO, "Retrieving formId...");

		String formIdFromResponse = JsonPath.read(JSON, "$.item.formId").toString().trim();
		reporter.log(Status.INFO, "Response formId: " + formIdFromResponse);
		return formIdFromResponse;
	}

	/**
	 * 
	 * @param JSON
	 * @return
	 * 
	 * @description This method extracts formHash from the provided JSON
	 */
	public String retrieveFormHash(String JSON) {
		reporter.log(Status.INFO, "Retrieving formHash...");

		String formHashFromResponse = JsonPath.read(JSON, "$.item.formHash").toString().trim();
		reporter.log(Status.INFO, "Response formHash: " + formHashFromResponse);
		return formHashFromResponse;
	}

	/**
	 * 
	 * @param JSON
	 * @param type
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * 
	 * @description This method accepts response JSON and, based on the response
	 *              body type, removes dynamic fields and eventually compares
	 *              expected and actual bodies
	 */
	public void verifyResponseBody(String JSON, ResponseBodyType type) throws JSONException, IOException, ParseException {

		switch (type) {
		case SAVE: {
			updatedResponse = removeFieldsFromJSON(JSON, "createTs", "electronicSignatureId");
			break;
		}
		case GET: {
			updatedResponse = removeFieldsFromJSON(JSON, "createTs", "electronicSignatureId");
			break;
		}
		case GETBYTTID: {
			updatedResponse = removeFieldsFromJSON(JSON, "createTs", "electronicSignatureId");
			break;
		}
		case INTENTBYID: {
			updatedResponse = JSON;
			break;
		}
		case SAVEINTENT: {
			updatedResponse = removeFieldsFromJSON(JSON, "item");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}

		reporter.log(Status.INFO, "Retrieveing expected body...");
		String expectedResponseData;
		switch (type) {
		case SAVE: {
			expectedResponseData = jsonResponseReader("saveBodyResponse");
			break;
		}
		case GET: {
			expectedResponseData = jsonResponseReader("getByIdResponse");
			break;
		}
		case GETBYTTID: {
			expectedResponseData = jsonResponseReader("getByTT&IdResponse");
			break;
		}
		case INTENTBYID: {
			expectedResponseData = jsonResponseReader("intentByIdResponse");
			break;
		}
		case SAVEINTENT: {
			expectedResponseData = jsonResponseReader("saveIntentResponse");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}

		reporter.log(Status.INFO, "Expected response body: " + expectedResponseData);
		reporter.log(Status.INFO, "Actual response body: " + response);

		reporter.log(Status.INFO, "Comparing response body with the expected body...");
		JSONAssert.assertEquals(expectedResponseData, updatedResponse, true);
	}
	/**
	 * This method returns a random Enum Constant from an Enum Class
	 * @param <T>
	 * @param enumClass
	 * @return
	 */
	public static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
		Random random = new Random();
		T[] enumConstants = enumClass.getEnumConstants();
		return enumConstants[random.nextInt(enumConstants.length)];
	}
}