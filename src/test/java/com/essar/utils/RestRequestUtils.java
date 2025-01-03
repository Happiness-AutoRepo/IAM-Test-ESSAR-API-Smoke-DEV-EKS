package com.essar.utils;

import static io.restassured.RestAssured.given;

import com.aventstack.customreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestRequestUtils extends CommonUtils {
	
	public static String saveESignatureResponseLoadBalancer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureLoadBalancer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}

	public static String getESignatureByIdLoadBalancer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("getESignatureByIdLoadBalancer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}

	public static String saveESignatureSaveIntentLoadBalancer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureSaveIntentLoadBalancer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}

	public static String saveESignatureGetIntentLoadBalancer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureGetIntentLoadBalancer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}

	public static String getEsignatureByTransTypeAndIdLoadBalancer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("getEsignatureByTransTypeAndIdLoadBalancer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	public static String saveESignatureGetIntentContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureGetIntentContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response= given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	
	public static String saveESignatureSaveIntentContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureSaveIntentContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response= given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	
	public static String getEsignaturesByTransTypeAndIdContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("getEsignatureByTransTypeAndIdContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	
	public static String getESignatureByIdContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("getESignatureByIdContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	
	public static String saveESignatureResponseContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveESignatureContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	
	/***
	 * MultiSignature save
	 * @param jsonRequest
	 * @return
	 */
	public static String saveMultiSignatureFormContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("saveMultiSignatureContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response= given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	/***
	 * MultiSignature extend
	 * @param jsonRequest
	 * @return
	 */
	public static String extendMultiSignatureFormContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("extendMultiSignatureContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response= given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
	/***
	 * MultiSignature retrieve
	 * @param jsonRequest
	 * @return
	 */
	public static String retrieveMultiSignatureFormContainer(String jsonRequest) {
		RestAssured.baseURI = ConfigurationReader.getProperty("retrieveMultiSignatureContainer");
		reporter.log(Status.INFO, "Sending request...");
		Response response= given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.contentType("application/json").body(jsonRequest).when().post();
		
		reporter.log(Status.INFO, "Response JSON: " + response.prettyPrint());
		return response.prettyPrint();
	}
}
