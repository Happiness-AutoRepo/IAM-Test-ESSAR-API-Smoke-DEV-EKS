package com.essar.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashGenerator {

	private static String hashValue;

	public static String getHashofJsonSHA512(String inputJson) {
		hashValue = Hashing.sha512().hashString(inputJson, StandardCharsets.UTF_8).toString();
		return hashValue;
	}

	public static String getHashofJsonSHA256(String inputJson) {
		hashValue = Hashing.sha256().hashString(inputJson, StandardCharsets.UTF_8).toString();
		return hashValue;
	}
	
}
