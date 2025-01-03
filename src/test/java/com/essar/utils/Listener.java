package com.essar.utils;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.customreports.Status;
import com.aventstack.customreports.markuputils.ExtentColor;
import com.aventstack.customreports.markuputils.MarkupHelper;

public class Listener extends CommonUtils implements ITestListener, ITestNGListener {

	public void onStart(ITestContext arg0) {
		report = CustomFactory.getInstance();
	}

	public void onTestStart(ITestResult arg0) {

	}

	public void onTestSkipped(ITestResult arg0) {
		reporter.log(Status.SKIP, "Test skipped: " + arg0.getName());
	}

	public void onTestSuccess(ITestResult arg0) {
		reporter.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult arg0) {
		String exceptionMessage = Arrays.toString(arg0.getThrowable().getStackTrace());
		reporter.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured: Click to see"
				+ "</font>" + "</b>" + "</summary>" + "<b>" + arg0.getThrowable().getMessage() + "</b>" + ""
				+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
	}

	public void onFinish(ITestContext arg0) {
		report.flush();
	}
}
