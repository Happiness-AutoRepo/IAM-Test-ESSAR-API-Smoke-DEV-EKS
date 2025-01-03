package com.essar.utils;

import java.io.File;

import com.aventstack.customreports.CustomReports;
import com.aventstack.customreports.reporter.ExtentHtmlReporter;
import com.aventstack.customreports.reporter.configuration.ChartLocation;
import com.aventstack.customreports.reporter.configuration.Theme;

public class CustomFactory {
	
	static ExtentHtmlReporter htmlReporter;
	static CustomReports extent;
	
	public static CustomReports getInstance() {

		createOutputFolder("./target/reports");
		
		htmlReporter = new ExtentHtmlReporter("./target/reports/Detailed_Results.html");
		extent = new CustomReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Environment", ConfigurationReader.getProperty("env"));
		extent.setSystemInfo("User Name", ConfigurationReader.getProperty("userName"));
		extent.setSystemInfo("OS", ConfigurationReader.getProperty("OS"));
		
		htmlReporter.config().setDocumentTitle("IAM Test ESSAR API Smoke " + ConfigurationReader.getProperty("env"));
		htmlReporter.config().setReportName("Daily Smoke Test Results - " + ConfigurationReader.getProperty("env"));
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		return extent;
	}

	private static void createOutputFolder(String path) {
		File outputFolder = new File(path);
		if (!outputFolder.exists()) {
			if (outputFolder.mkdir()) {
				System.out.println("Output directory has been created");
			} else {
				System.out.println("Failed to create Directory");
			}
		} else {
			System.out.println("Diretory already exists");
		}
	}
}