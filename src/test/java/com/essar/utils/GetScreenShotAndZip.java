package com.essar.utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

import com.essar.annotations.Description;
import com.essar.custom.report.utils.ReportBuilder;
import com.essar.custom.report.utils.ZipUtils;

@Description("This class take screenshots of reports and zip them after exectution of test suite")
@Listeners(Listener.class)
public class GetScreenShotAndZip extends CommonUtils {

	@AfterSuite
	public void zipReports() throws InterruptedException {
		
		ZipUtils.zipReports();
		if("false".equals(System.getProperty("buildReport"))) {
			return;
		}
		ReportBuilder.getReport();
	}
}
