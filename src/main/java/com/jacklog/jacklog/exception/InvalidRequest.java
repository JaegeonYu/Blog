package com.jacklog.jacklog.exception;

public class InvalidRequest extends JackException{
    private static final String MESSAGE = "잘못된 요청입니다.";
    public InvalidRequest() {
        super(MESSAGE);
    }


    @Override
    public int getStatusCode() {
        return 400;
    }
}
