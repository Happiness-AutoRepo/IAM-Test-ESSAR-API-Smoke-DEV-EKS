package com.aventstack.customreports;

import com.aventstack.customreports.model.Media;

public class MediaEntityModelProvider {

	private Media m;
	
	public MediaEntityModelProvider(Media m) {
		this.m = m;
	}
	
	public Media getMedia() {
		return m;
	}

}
