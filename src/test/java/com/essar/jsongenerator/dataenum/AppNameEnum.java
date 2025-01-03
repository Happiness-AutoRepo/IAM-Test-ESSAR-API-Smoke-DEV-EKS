package com.essar.jsongenerator.dataenum;

import java.util.Random;

public enum AppNameEnum {
	TaxPro_Default("TaxPro"),
	OLA_Default("OLA"),
	IVES_Default("IVES_OLA"),
	IVESBOLA_Default("WA-BOLA-IV"),
	CleanEnergy_Default("WA-CE"),
	BOLA_Default("WA-BOLA");
	
	
	private String value;

	private AppNameEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
		Random random = new Random();
		T[] enumConstants = enumClass.getEnumConstants();
		return enumConstants[random.nextInt(enumConstants.length)];
	}

}
