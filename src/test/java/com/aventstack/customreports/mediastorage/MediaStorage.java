package com.aventstack.customreports.mediastorage;

import java.io.IOException;

import com.aventstack.customreports.model.Media;

public interface MediaStorage {
    void init(String v) throws IOException;
    void storeMedia(Media m) throws IOException;
}
