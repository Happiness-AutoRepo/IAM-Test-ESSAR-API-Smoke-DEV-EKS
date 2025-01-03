package com.essar.custom.report.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.essar.utils.CommonUtils;
import com.essar.utils.ConfigurationReader;;


public class ReportBuilder extends CommonUtils {
	
	private static WebDriver RBdriver;
	
	private static String getChromedriverPath() {
		if("CICD".equals(System.getProperty("executionLocation"))) {
			System.out.println("Using chrome binary on MTB_BAD34");
			return ConfigurationReader.getProperty("chrome.driver.path.CICD");
		} else {
			System.out.println("Using chrome binary on CoE/Local");
			return ConfigurationReader.getProperty("chrome.driver.path");
		}
	}
	
	public static void getReport() throws InterruptedException {
		switch (ConfigurationReader.getProperty("report")) {
		case "chrome":
			chromeReport();
			break;
		case "firefox":
			firefoxReport();
			break;
		}	
	}
	
	private static void setUpChrome() {
		
		System.setProperty("webdriver.chrome.driver", getChromedriverPath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-gpu");
		options.addArguments("window-size=1680x1080");
		options.addArguments("--disable-infobars");
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		RBdriver = new ChromeDriver(options);
		RBdriver.manage().window().setSize(new Dimension(1680,1080));
//		RBdriver.manage().window().fullscreen();
	}
	
	private static void setUpFirefox() {
		
		System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("gecko.driver.path"));
		FirefoxBinary firefoxBinary2 = new FirefoxBinary();
		firefoxBinary2.addCommandLineOptions("--headless");				
		DesiredCapabilities firefoxCaps2 = new DesiredCapabilities();
		firefoxCaps2.setCapability("acceptInsecureCerts", true);	
		firefoxCaps2.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
		FirefoxOptions firefoxOptions2 = new FirefoxOptions();
//		firefoxOptions2.addCapabilities(firefoxCaps2);
		firefoxOptions2.setBinary(firefoxBinary2);
		RBdriver = new FirefoxDriver(firefoxOptions2);
		RBdriver.manage().window().setSize(new Dimension(1680,1050));
		RBdriver.manage().window().fullscreen();
	}
	
	private static void RBtakeScreenshot(String name) {
		
		File scrFile = ((TakesScreenshot)RBdriver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target";
            File destFile = new File((String) reportDirectory+"/reports/"+name+".png");
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void chromeReport() throws InterruptedException {
		
		String base = "file:///" + new File(System.getProperty("user.dir")).getAbsolutePath();
		
		setUpChrome();
		
		RBdriver.navigate().to(base + "/target/reports/Detailed_Results.html");
		RBdriver.findElement(By.cssSelector("a[view='dashboard-view']")).click();
		
		Thread.sleep(2000);
		RBtakeScreenshot("Report");
		Thread.sleep(5000);
		RBdriver.quit();
	}
	
	private static void firefoxReport() throws InterruptedException {
		
		String base = "file:///" + new File(System.getProperty("user.dir")).getAbsolutePath();
		
		setUpFirefox();
		RBdriver.navigate().to(base + "/target/reports/Detailed_Results.html");
		RBdriver.findElement(By.cssSelector("a[view='dashboard-view']")).click();
		
		Thread.sleep(3000);
		RBtakeScreenshot("Report");
		Thread.sleep(3000);
		
		RBdriver.quit();
	}
}
