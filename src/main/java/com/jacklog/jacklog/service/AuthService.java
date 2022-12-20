package com.jacklog.jacklog.service;

import com.jacklog.jacklog.domain.Session;
import com.jacklog.jacklog.domain.User;
import com.jacklog.jacklog.exception.InvalidSigninInformation;
import com.jacklog.jacklog.repository.UserRepository;
import com.jacklog.jacklog.request.LogIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public String signin(LogIn logIn){
        User user = userRepository.findByEmailAndPassword(logIn.getEmail(), logIn.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        Session session = user.addSession();
        return session.getAccessToken();
    }
}
