package com.jacklog.jacklog.exception;

public class DuplicateEmail extends JackException {
    private static final String MESSAGE = "이미 가입된 이메일입니다.";

    public DuplicateEmail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
