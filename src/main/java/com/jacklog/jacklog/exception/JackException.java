package com.jacklog.jacklog.exception;

public abstract class JackException extends RuntimeException{
    public JackException(String message) {
        super(message);
    }


    public abstract int getStatusCode();
}
