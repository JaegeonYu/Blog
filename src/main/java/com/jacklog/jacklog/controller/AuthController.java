package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.domain.User;
import com.jacklog.jacklog.exception.InvalidSigninInformation;
import com.jacklog.jacklog.repository.UserRepository;
import com.jacklog.jacklog.request.LogIn;
import com.jacklog.jacklog.response.SessionResponse;
import com.jacklog.jacklog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody LogIn login){
        String accessToken = authService.signin(login);
        return new SessionResponse(accessToken);
    }

}
