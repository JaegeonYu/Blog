package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;
    @BeforeEach
    public void beforeEach(){
        postRepository.deleteAll();
    }
    @Test
    @DisplayName("/posts 요청시 hello world 출력")
    public void test() throws Exception{
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\":\"내용입니다\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 valid에 따라 Map 반환")
    public void test2() throws Exception{
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\", \"content\":\"내용입니다\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));
    }
    @Test
    @DisplayName("/posts 요청시 저장")
    public void test3() throws Exception{
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\", \"content\":\"내용입니다\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        Assertions.assertEquals(postRepository.count(),1L);
    }

}