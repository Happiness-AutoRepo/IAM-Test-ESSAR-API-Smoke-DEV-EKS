package com.essar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConverter {
	private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

	private static String StandardDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static String getStandardFormat() {
		return StandardDateFormat;
	}

	public static String DateToString(Date date) {
		return DateToString(date, null);
	}

	public static String DateToString(Date date, String format) {
		String output = "";

		// Use standard format if not provided.
		if (format == null || format.length() == 0) {
			format = StandardDateFormat;
		}

		// Only convert non-null dates.
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				output = sdf.format(date);
			} catch (Exception e) {
				logger.debug("Failed to convert date(date: " + date + ")");
			}
		}

		return output;
	}

	public static Date StringToDate(String date) {
		return StringToDate(date, null);
	}

	public static Date StringToDate(String date, String format) {
		Date output = null;

		// Use standard format if not provided.
		if (format == null || format.length() == 0) {
			format = StandardDateFormat;
		}

		// Only convert non-null dates.
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				output = sdf.parse(date);
			} catch (Exception e) {
				logger.debug("Failed to convert date(date: " + date + ")");
			}
		}

		return output;
	}	
}