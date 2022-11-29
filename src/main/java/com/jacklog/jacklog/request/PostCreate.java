package com.jacklog.jacklog.request;

import lombok.Getter;

@Getter
public class PostCreate {
    private String title;
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
