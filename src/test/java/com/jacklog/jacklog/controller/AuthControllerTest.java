package com.jacklog.jacklog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacklog.jacklog.domain.User;
import com.jacklog.jacklog.repository.SessionRepository;
import com.jacklog.jacklog.repository.UserRepository;
import com.jacklog.jacklog.request.LogIn;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
        sessionRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공 ")
    public void test() throws Exception {
        //given
        User user = User.builder().email("yjk9805@naver.com")
                .password("1234")
                .name("유재건")
                .build();
        userRepository.save(user);
        //when
        LogIn logIn = LogIn.builder()
                .email("yjk9805@naver.com")
                .password("1234")
                .build();
        String request = objectMapper.writeValueAsString(logIn);
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Transactional
    @DisplayName("로그인 성공 후 세션토큰 1개 발급")
    public void tes2() throws Exception {
        //given
        User user = User.builder().email("yjk9805@naver.com")
                .password("1234")
                .name("유재건")
                .build();
        userRepository.save(user);
        //when
        LogIn logIn = LogIn.builder()
                .email("yjk9805@naver.com")
                .password("1234")
                .build();
        String request = objectMapper.writeValueAsString(logIn);
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()));
        //then
        Assertions.assertEquals(1L, user.getSessions().size());
    }

}