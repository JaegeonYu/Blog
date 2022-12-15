package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.exception.JackException;
import com.jacklog.jacklog.exception.PostNotFound;
import com.jacklog.jacklog.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ErrorResponse response = ErrorResponse.two()
                .code("400")
                .message("잘못된 요청입니다.").
                build();
        for(FieldError tmp :e.getFieldErrors()){
            System.out.println(tmp);
            response.addValidation(tmp.getField(), tmp.getDefaultMessage());
        }
        return response;
    }

    @ExceptionHandler(JackException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> jackException(JackException e){
        int statusCode = e.getStatusCode();
        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(e.getStatusCode()))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode).body(body);
        return response;
    }
}
