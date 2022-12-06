package com.jacklog.jacklog.service;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.repository.PostRepository;
import com.jacklog.jacklog.request.PostCreate;
import com.jacklog.jacklog.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("서비스 레이어 글 작성 테스트")
    public void test() throws Exception{
        //given
        PostCreate post = PostCreate.builder()
                .title("foo")
                .content("bar")
                .build();
        //when
        postService.write(post);
        //then

        Assertions.assertEquals(1L, postRepository.count());
    }


    @Test
    @DisplayName("글 조회 테스트")
    public void test2() throws Exception{
        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        //when
        postRepository.save(requestPost);
        //then
        PostResponse post = postService.get(requestPost.getId());
        Assertions.assertEquals(post.getTitle(), requestPost.getTitle());
        Assertions.assertEquals(post.getContent(), requestPost.getContent());
    }
    @Test
    @DisplayName("응답 클래스 분리 테스트")
    public void test3() throws Exception{
        //given
        Post requestPost = Post.builder()
                .title("012345678912345")
                .content("bar")
                .build();
        //when
        postRepository.save(requestPost);
        //then
        PostResponse response = postService.get(requestPost.getId());
        Assertions.assertEquals(response.getTitle(), "0123456789");
    }
    @Test
    @DisplayName("게시글 여러개 조회 테스트")
    public void test4() throws Exception{
        //given
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        Post post2 = Post.builder()
                .title("title2")
                .content("content2")
                .build();
        postRepository.save(post);
        postRepository.save(post2);
        //when
        List<PostResponse> responses = postService.getList();

        //then
        assertEquals(responses.size(), 2L);
    }
}