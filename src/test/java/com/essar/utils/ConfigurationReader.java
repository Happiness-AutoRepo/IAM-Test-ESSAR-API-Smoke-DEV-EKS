package com.essar.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

	private static Properties configFile;

	static {

		try {
			String path = "./src/test/resources/config/configuration.properties";
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}
	
	public static void setProperty(String key, String value) {
		configFile.setProperty(key, value);
	}
}
