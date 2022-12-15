package com.jacklog.jacklog.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> validation = new HashMap<>();
    @Builder(builderMethodName = "two")
    public ErrorResponse(String code, String message){
        this.code = code;
        this.message = message;
        this.validation = new HashMap<>();
    }
    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }
    public void addValidation(String field, String message){
        this.validation.put(field, message);
    }
}
