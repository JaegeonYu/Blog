package com.jacklog.jacklog.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostCreate {
    @NotBlank
    private String title;
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
