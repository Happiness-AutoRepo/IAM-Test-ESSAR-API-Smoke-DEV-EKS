package com.essar.custom.report.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

@SuppressWarnings("restriction")
public class IDMUtils {

	/**
	 * This method gives a hashed representation of an input string. SSN must be
	 * input plain (666542314), phones need to be input with hyphens (919-435-7651)
	 * 
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		byte[] tinBytes = input.getBytes("UTF-8");
		md.update(tinBytes);
		byte[] mdHashBytes = md.digest();
		String base64Str = (new Base64()).encode(mdHashBytes).toString(); // encode to Base-64
		return base64Str;
	}

	/**
	 * This method inserts hyphens into plane phone numbers. Example: 9196211017
	 * becomes 919-621-1017
	 * 
	 * @param phoneWithoutHyphens
	 * @return
	 */
	public static String insertHyphensIntoPhoneNumber(String phoneWithoutHyphens) {
		return phoneWithoutHyphens.substring(0, 3) + "-" + phoneWithoutHyphens.substring(3, 6) + "-"
				+ phoneWithoutHyphens.substring(6, 10);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String input = "666610239";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		byte[] tinBytes = input.getBytes("UTF-8");
		md.update(tinBytes);
		byte[] mdHashBytes = md.digest();
		String base64Str = (new Base64()).encode(mdHashBytes).toString(); // encode to Base-64
		System.out.println(base64Str);
	}

	public static String getApacheHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		byte[] tinBytes = input.getBytes("UTF-8");
		md.update(tinBytes);
		byte[] mdHashBytes = md.digest();
		Base64 base64 = new Base64();
		String base64Str = new String(base64.encode(mdHashBytes));
		return base64Str;
	}
}