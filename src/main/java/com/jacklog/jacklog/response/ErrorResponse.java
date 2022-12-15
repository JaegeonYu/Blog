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
    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public void addValidation(String field, String message){
        validation.put(field, message);
    }
}
