package com.aventstack.customreports.reporter.converters;

import java.util.List;

import com.aventstack.customreports.model.Test;

public class ExtentHtmlReporterConverter {
	
	private String filePath;

	public ExtentHtmlReporterConverter(String filePath) {
		this.filePath = filePath;
	}
		
	public List<Test> parseAndGetModelCollection() {
		ExtentHtmlTestConverter converter = new ExtentHtmlTestConverter(filePath);
		List<Test> testList = converter.parseAndGetTests();
		return testList;
	}
	
}
