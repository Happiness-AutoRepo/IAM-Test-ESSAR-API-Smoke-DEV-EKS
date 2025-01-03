package com.essar.custom.report.utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.essar.utils.ConfigurationReader;

public class Driver {

	private static WebDriver driver;

	public static WebDriver getInstance() {
		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			switch (ConfigurationReader.getProperty("browser")) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("gecko.driver.path"));				
				DesiredCapabilities firefoxCaps1 = new DesiredCapabilities();
				firefoxCaps1.setCapability("acceptInsecureCerts", true);	
				firefoxCaps1.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
				FirefoxOptions firefoxOptions1 = new FirefoxOptions();
//				firefoxOptions1.addCapabilities(firefoxCaps1);
				driver = new FirefoxDriver(firefoxOptions1);
				break;
			case "headless_firefox":
				System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("gecko.driver.path"));
				FirefoxBinary firefoxBinary2 = new FirefoxBinary();
				firefoxBinary2.addCommandLineOptions("--headless");				
				DesiredCapabilities firefoxCaps2 = new DesiredCapabilities();
				firefoxCaps2.setCapability("acceptInsecureCerts", true);	
				firefoxCaps2.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
				FirefoxOptions firefoxOptions2 = new FirefoxOptions();
//				firefoxOptions2.addCapabilities(firefoxCaps2);
				firefoxOptions2.setBinary(firefoxBinary2);
				driver = new FirefoxDriver(firefoxOptions2);
				break;
			case "headless_chrome":
				System.setProperty("webdriver.chrome.driver", ConfigurationReader.getProperty("chrome.driver.path"));
				ChromeOptions options = new ChromeOptions();
		        options.addArguments("window-size=1200x600");
		        options.addArguments("--headless=new");
		        options.setAcceptInsecureCerts(true);
		        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				driver = new ChromeDriver(options);
				break;
			default:
				System.setProperty("webdriver.chrome.driver", ConfigurationReader.getProperty("chrome.driver.path"));
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("window-size=1200x600");
				opt.addArguments("--disable-infobars");
				opt.setAcceptInsecureCerts(true);
				opt.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				driver = new ChromeDriver(opt);
				break;
			}
		}
		return driver;
	}

	public static void closeDriver() {
		
		if (driver != null) {
			
			try {
				driver.quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			driver = null;
		}
	}
}
