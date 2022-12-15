package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.exception.PostNotFound;
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
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.").
                build();
        for(FieldError tmp :e.getFieldErrors()){
            response.addValidation(tmp.getField(), tmp.getDefaultMessage());
        }
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFound.class)
    @ResponseBody
    public ErrorResponse postNotFound(PostNotFound e){
        ErrorResponse response = ErrorResponse.builder()
                .code("404")
                .message(e.getMessage()).
                build();
        return response;
    }
}
