package com.essar.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.essar.annotations.Description;
import com.essar.annotations.Title;
import com.essar.utils.CommonUtils;
import com.essar.utils.Listener;
import com.essar.utils.RestRequestUtils;
import com.essar.utils.TestCategory;
import com.jayway.jsonpath.JsonPath;

@Description("SaveESignatureServiceTest - Verify valid saveElectronicSignature TimeStamp")
@Listeners(Listener.class)
public class SaveESignatureTimeStampsTest extends CommonUtils {
	
	@BeforeClass
	public void init(){
		startLogging("SaveESignatureServiceTest", "Verify valid saveElectronicSignature TimeStamp", TestCategory.CONTAINER);
	}

	@Title("SaveElectronicSignatureServiceTest - Verify that service returns correct TimeStamp on Container")
	@Test(priority = 0)
	public void saveESignatureStatusTimeStampCheckContainerSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureStatusTimestampSuccess", "Verify that service is returning 200 for successfull response", TestCategory.CONTAINER);
		String responseContainer = RestRequestUtils.saveESignatureResponseContainer(updateJSON(requestFilePath("saveRequest")));

		Instant now = Instant.now();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(now, ZoneId.of("UTC"));
		System.out.println("this is the utc zoned date time   : " + zdt);

		String timeStampContainer = JsonPath.read(responseContainer, "$.item.createTs").toString();

		Instant utcFromZDT = zdt.toInstant();
		Instant tsFromRespone = Instant.parse(timeStampContainer);

		Long noOfMillis = Duration.between(tsFromRespone, utcFromZDT).toMillis();

		long limitofMillis = 10000;

		boolean sameTimeZone = noOfMillis < limitofMillis;
		assertEquals(true, sameTimeZone);
	}

	@Title("SaveElectronicSignatureServiceTest - Verify that service returns correct TimeStamp on LoadBalancer")
	@Test(priority = 1)
	public void saveESignatureStatusTimeStampCheckLoadBalancerSuccess() throws IOException, JSONException, ParseException {

		childTestLogging("SaveESignatureStatusTimestampSuccess", "Verify that service is returning 200 for successfull response", TestCategory.LOADBALANCER);
		String response = RestRequestUtils.saveESignatureResponseLoadBalancer(updateJSON(requestFilePath("saveRequest")));

		Instant now = Instant.now();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(now, ZoneId.of("UTC"));
		System.out.println("this is the utc zoned date time   : " + zdt);

		String timeStamp = JsonPath.read(response, "$.item.createTs").toString();

		Instant utcFromZDT = zdt.toInstant();
		Instant tsFromRespone = Instant.parse(timeStamp);

		Long noOfMillis = Duration.between(tsFromRespone, utcFromZDT).toMillis();

		long limitofMillis = 10000;

		boolean sameTimeZone = noOfMillis < limitofMillis;
		assertEquals(true, sameTimeZone);
	}
}