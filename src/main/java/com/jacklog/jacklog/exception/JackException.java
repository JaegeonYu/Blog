package com.jacklog.jacklog.exception;

public abstract class JackException extends RuntimeException{
    public JackException(String message) {
        super(message);
    }

    public JackException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
