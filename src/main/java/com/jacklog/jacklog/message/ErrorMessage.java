package com.jacklog.jacklog.message;

public enum ErrorMessage {
    POST_NOT_EXIST("존재하지 않는 글입니다.");

    private String message;
    private final String prefix = "[ERROR]";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix + message;
    }
}
