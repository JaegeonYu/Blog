package com.jacklog.jacklog.request;

import com.jacklog.jacklog.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostCreate {
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    private String content;
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate(){
        if(title.contains("바보"))
            throw new InvalidRequest();
    }
}
