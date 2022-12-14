package com.jacklog.jacklog.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostEdit {
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    @NotBlank(message = "콘텐츠를 입력하세요")
    private String content;
    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
