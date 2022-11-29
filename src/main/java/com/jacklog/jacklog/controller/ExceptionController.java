package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e){
        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다");
        for(FieldError tmp :e.getFieldErrors()){
            response.addValidation(tmp.getField(), tmp.getDefaultMessage());
        }
        return response;
    }
}
