package com.jacklog.jacklog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacklog.jacklog.domain.Session;
import com.jacklog.jacklog.domain.User;
import com.jacklog.jacklog.exception.Unauthorized;
import com.jacklog.jacklog.repository.SessionRepository;
import com.jacklog.jacklog.repository.UserRepository;
import com.jacklog.jacklog.request.LogIn;
import com.jacklog.jacklog.request.SignUp;
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

    @Test
    @DisplayName("로그인 안하고 권한필요 사이트 접근 /test2")
    public void tes3() throws Exception {
        //given
        mockMvc.perform(get("/test2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("로그인 후 권한필요 페이지 접속 /test2")
    public void tes4() throws Exception {
        //given
        User user = User.builder().email("yjk9805@naver.com")
                .password("1234")
                .name("유재건")
                .build();
        Session session = user.addSession();
        userRepository.save(user);
        //when

        mockMvc.perform(get("/test2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", session.getAccessToken()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한 필요 페이지 접속불가")
    public void tes5() throws Exception {
        //given
        User user = User.builder().email("yjk9805@naver.com")
                .password("1234")
                .name("유재건")
                .build();
        Session session = user.addSession();
        userRepository.save(user);
        //when

        mockMvc.perform(get("/test2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", session.getAccessToken()+"-"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
    @Test
    @DisplayName("회원 가입 테스트")
    public void test6() throws Exception {
        SignUp signUp = SignUp.builder()
                .name("hello")
                .email("gigi@gmail.com")
                .password("12341234")
                .build();
        String requestSignUp = objectMapper.writeValueAsString(signUp);
        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestSignUp))
                .andDo(print());
        Assertions.assertEquals(userRepository.count(), 1);
    }

    @Test
    @DisplayName("회원 가입 이메일 중복 테스트 throw DuplicateEmail")
    public void test7() throws Exception {
        //given
        User user = User.builder()
                .name("hello")
                .email("gigi@gmail.com")
                .password("12341234")
                .build();
        userRepository.save(user);
        // when
        SignUp signUp = SignUp.builder()
                .name("hello")
                .email("gigi@gmail.com")
                .password("123")
                .build();
        String requestSignUp = objectMapper.writeValueAsString(signUp);
        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestSignUp))
                .andExpect(status().isUnauthorized());
    }

}