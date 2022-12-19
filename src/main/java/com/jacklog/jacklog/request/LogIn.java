package com.jacklog.jacklog.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LogIn {
    @NotBlank(message = "이메일을 입력하세요")
    private String email;
    @NotBlank(message = "비밀번호를 입려하세요")
    private String password;

    public LogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
