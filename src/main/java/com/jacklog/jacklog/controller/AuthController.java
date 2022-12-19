package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.domain.User;
import com.jacklog.jacklog.exception.InvalidSigninInformation;
import com.jacklog.jacklog.repository.UserRepository;
import com.jacklog.jacklog.request.LogIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRepository userRepository;

    @PostMapping("/auth/login")
    public User login(@RequestBody LogIn login){
        log.info(">> log info ={}", login);
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        return user;
    }

}
