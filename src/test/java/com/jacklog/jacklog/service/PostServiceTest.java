package com.jacklog.jacklog.service;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.repository.PostRepository;
import com.jacklog.jacklog.request.PostCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Post post = postService.get(requestPost.getId());
        Assertions.assertEquals(post.getTitle(), requestPost.getTitle());
        Assertions.assertEquals(post.getContent(), requestPost.getContent());
    }
}