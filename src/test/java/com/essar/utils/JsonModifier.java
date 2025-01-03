package com.essar.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.aventstack.customreports.Status;

public class JsonModifier extends CommonUtils {

	public static String JsonToXML(String json) {

		String xml = "<Response>";

		try {

			JSONObject jo = new JSONObject(json);

			xml = xml + XML.toString(jo);

		} catch (Exception e) {
			System.out.println(e);
		}

		xml = xml + "</Response>";

		return xml;
	}

	public static String JsonArrayToXML(String json) {

		String xml = "<Response>";

		try {

			JSONArray jo = new JSONArray(json);

			xml = xml + XML.toString(jo);

		} catch (Exception e) {
			System.out.println(e);
		}

		xml = xml + "</Response>";

		return xml;
	}

	public static String updateJsonResponse(String response, String enrollType) throws JSONException {

		String fields[] = {"createTs", "electronicSignatureId"};

		JSONObject obj = null;

		if (enrollType.toLowerCase().trim().equals("updatedjson")) {
			obj = JsonModifier.removeJSONField(new JSONObject(response), fields);
		}

		return obj.toString();
	}
	
	public static String removeFieldsFromJSON(String JSON, String... fields) throws JSONException {

		reporter.log(Status.INFO, "Removing dynamic fields from the response body: " + Arrays.toString(fields));
		JSONObject obj = JsonModifier.removeJSONField(new JSONObject(JSON), fields);

		return obj.toString();
	}

	public static String updateJsonResponseGet(String response, String enrollType) throws JSONException {

		String fields[] = {"createTs"};

		JSONObject obj = null;

		if (enrollType.toLowerCase().trim().equals("updatedjson")) {
			obj = JsonModifier.removeJSONField(new JSONObject(response), fields);
		}

		return obj.toString();
	}

	public static String updateJsonResponseSaveIntent(String response) throws JSONException {

		String fields[] = {"item"};

		JSONObject obj = null;

		obj = JsonModifier.removeJSONField(new JSONObject(response), fields);

		return obj.toString();
	}

	static String rootKey = "Str";

	public static JSONObject removeNullFields(Object object) throws JSONException {
		JSONObject json = null;
		if (object instanceof JSONArray) {
			JSONArray array = (JSONArray) object;
			for (int i = 0; i < array.length(); ++i)
				removeNullFields(array.get(i));
		} else if (object instanceof JSONObject) {
			json = (JSONObject) object;
			JSONArray names = json.names();
			for (int i = 0; i < names.length(); ++i) {
				String key = names.getString(i);
				if (json.isNull(key)) {
					json.remove(key);
				} else {
					removeNullFields(json.get(key));
				}
			}
		}
		return json;
	}

	public static JSONObject removeJSONField(Object object, String[] keyArray) throws JSONException {
		JSONObject json = null;
		for (int j = 0; j < keyArray.length; j++) {

			if (object instanceof JSONArray) {
				JSONArray array = (JSONArray) object;
				for (int i = 0; i < array.length(); ++i)
					removeJSONField(array.get(i), new String[] { keyArray[j] });
			} else if (object instanceof JSONObject) {
				json = (JSONObject) object;
				JSONArray names = json.names();
				for (int i = 0; i < names.length(); ++i) {
					String key = names.getString(i);
					if (key.equals(keyArray[j])) {
						json.remove(key);
					} else {
						removeJSONField(json.get(key), new String[] { keyArray[j] });
					}
				}
			}
		}

		return json;
	}

	public static JSONObject updateJson(JSONObject obj, String ObjectKey, String keyString, String newValue)
			throws Exception {
		// JSONObject json = new JSONObject();

		// get the keys of json object
		Iterator<?> iterator = obj.keys();
		String key = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			// System.out.println(key);
			if (key.equals(ObjectKey)) {
				rootKey = key.toString();
			}
			// if the key is a string, then update the value
			if ((obj.optJSONArray(key) == null) && (obj.optJSONObject(key) == null)) {
				if ((key.equals(keyString))) {
					// put new value
					if (rootKey.equals(ObjectKey)) {
						obj.put(key, newValue);
						rootKey = "Str";
						return obj;
					} else {
						return obj;
					}
				}
			}

			// if it's jsonobject
			if (obj.optJSONObject(key) != null) {
				updateJson(obj.getJSONObject(key), ObjectKey, keyString, newValue);
			}

			// if it's jsonarray
			if (obj.optJSONArray(key) != null) {
				JSONArray jArray = obj.getJSONArray(key);
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jj = jArray.optJSONObject(0);
					if (jj != null) {
						updateJson(jArray.getJSONObject(i), ObjectKey, keyString, newValue);
					}
				}
			}
		}
		return obj;
	}

	public static List<String> expListFromArrayOfObjectsByValue(String path, String responseElement,
			String specificPlan) throws IOException, JSONException {
		List<String> expectedList = new ArrayList<>();
		String fileName = "src/test/resources/response/" + path + ".json";
		String expectedJson = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

		JSONObject jsonObject = new JSONObject(expectedJson);
		JSONArray jsonExpected = jsonObject.getJSONArray(specificPlan);

		for (int i = 0; i < jsonExpected.length(); i++) {
			String valueExpected = jsonExpected.getJSONObject(i).getString(responseElement);
			expectedList.add(valueExpected);
		}

		return expectedList;
	}

	public static List<String> expListFromNestedArrayOfObjects(String path, String value, String responseElement)
			throws IOException, JSONException {
		List<String> expectedList = new ArrayList<>();

		String fileName = "src/test/resources/response/" + path + ".json";
		String expectedJson = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

		JSONObject jsonObject = new JSONObject(expectedJson);
		JSONArray jsonExpected = jsonObject.getJSONArray(value);

		for (int i = 0; i < jsonExpected.length(); i++) {
			JSONObject jsonObject1 = jsonExpected.getJSONObject(i);
			JSONArray jsonArray = jsonObject1.getJSONArray("otherFromAccountRules");

			for (int j = 0; j < jsonArray.length(); j++) {

				String actValue = jsonArray.getJSONObject(j).getString(responseElement);
				expectedList.add(actValue);
			}
		}

		return expectedList;
	}

	public static List<String> jsonUserCredentials(String jsonDataFile, String jsonTag, String userId, String userPwd)
			throws IOException, JSONException {
		List<String> jsonUserCredentials = new ArrayList<>();

		String testData = "src/test/resources/testData/" + jsonDataFile + ".json";
		String participantData = new String(Files.readAllBytes(Paths.get(testData)), StandardCharsets.UTF_8);

		JSONObject obj = new JSONObject(participantData);
		JSONArray jsonArray = obj.getJSONArray(jsonTag);

		for (int i = 0; i < jsonArray.length(); i++) {
			if (jsonArray.optJSONObject(i).has(userId)) {
				String userIdValue = jsonArray.getJSONObject(i).getString(userId);
				jsonUserCredentials.add(userIdValue);
				String userPwdValue = jsonArray.getJSONObject(i).getString(userPwd);
				jsonUserCredentials.add(userPwdValue);
				return jsonUserCredentials;
			}
		}
		return null;
	}

	public static List<String> jsonSpecificUserCredentials(String jsonDataFile, String jsonTag, String specificID,
			String userId, String userPwd) throws IOException, JSONException {
		List<String> jsonUserCredentials = new ArrayList<>();

		String testData = "src/test/resources/testData/" + jsonDataFile + ".json";
		String participantData = new String(Files.readAllBytes(Paths.get(testData)), StandardCharsets.UTF_8);

		JSONObject obj = new JSONObject(participantData);
		JSONArray jsonArray = obj.getJSONArray(jsonTag);

		for (int i = 0; i < jsonArray.length(); i++) {
			if (jsonArray.optJSONObject(i).has(specificID)) {
				String userIdValue = jsonArray.getJSONObject(i).getString(userId);
				jsonUserCredentials.add(userIdValue);
				String userPwdValue = jsonArray.getJSONObject(i).getString(userPwd);
				jsonUserCredentials.add(userPwdValue);
				return jsonUserCredentials;
			}
		}
		return null;
	}
}
