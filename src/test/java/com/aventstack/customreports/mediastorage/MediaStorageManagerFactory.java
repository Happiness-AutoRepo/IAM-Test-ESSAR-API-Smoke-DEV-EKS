package com.aventstack.customreports.mediastorage;

public class MediaStorageManagerFactory {
    public MediaStorage getManager(String location) {
        switch (location.trim().toLowerCase()) {
            case "http":
                return new HttpMediaManager();
            case "http-klov":
                return new HttpMediaManagerKlov();
            default:
                return null;
        }
    }
}
