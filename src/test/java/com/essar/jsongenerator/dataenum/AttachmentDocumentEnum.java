package com.essar.jsongenerator.dataenum;

public enum AttachmentDocumentEnum {

	Document("Document"),
	Image("Image"),
	Pointer("Pointer");

	private String value;

	private AttachmentDocumentEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
