package com.aventstack.customreports;

@SuppressWarnings("serial")
public class CustomTestInterruptedException extends RuntimeException {
    public CustomTestInterruptedException(Throwable t) {
        super(t);
    }

    public CustomTestInterruptedException(String string) {
        super(string);
    }

    public CustomTestInterruptedException(String string, Throwable t) {
        super(string, t);
    }   

}
