package com.jacklog.jacklog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public abstract class JackException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();
    public JackException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message){
        validation.put(fieldName, message);
    }
}
