package com.jacklog.jacklog.service;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.exception.PostNotFound;
import com.jacklog.jacklog.repository.PostRepository;
import com.jacklog.jacklog.request.PostCreate;
import com.jacklog.jacklog.request.PostEdit;
import com.jacklog.jacklog.request.PostSearch;
import com.jacklog.jacklog.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @BeforeEach
    public void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("서비스 레이어 글 작성 테스트")
    public void test() throws Exception {
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
    public void test2() throws Exception {
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
    public void test3() throws Exception {
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
    public void test4() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("Bebe title " + i)
                        .content("content " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);
        //when
        PostSearch postSearch = PostSearch.builder()
                .build();
        List<PostResponse> responses = postService.getList(postSearch);

        //then
        assertEquals(responses.size(), 10L);
        assertEquals(responses.get(0).getTitle(), "Bebe title 19");
    }

    @Test
    @DisplayName("글 1페이지 조회")
    public void test5() {
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("Bebe title " + i)
                        .content("content " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);
        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        List<PostResponse> posts = postService.getList(postSearch);
        assertEquals(10L, posts.size());
        assertEquals("Bebe title 30", posts.get(0).getTitle());
        assertEquals("Bebe title 26", posts.get(4).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    public void tes6() {
        Post post = Post.builder()
                .title("bebe")
                .content("content")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("jack")
                .build();

        postService.edit(post.getId(), postEdit);
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(PostNotFound::new);
        Assertions.assertEquals("jack", changedPost.getTitle());
    }

    @Test
    @DisplayName("글 삭제")
    public void test7() {
        Post post = Post.builder()
                .title("bebe")
                .content("content")
                .build();

        postRepository.save(post);

        postService.delete(post.getId());
        Assertions.assertEquals(postRepository.count(), 0);
    }

    @Test
    @DisplayName("글 조회 예외 테스트")
    public void test8() throws Exception {
        //given
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        //when
        postRepository.save(post);
        //then
        assertThatThrownBy(() -> postService.get(post.getId() + 1L))
                .isInstanceOf(PostNotFound.class)
                .hasMessageContaining("존재하지");
    }

    @Test
    @DisplayName("글 제목 수정 예외 테스트")
    public void tes9() {
        Post post = Post.builder()
                .title("bebe")
                .content("content")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("jack")
                .build();

        assertThatThrownBy(() -> postService.edit(post.getId() + 1L, postEdit))
                .isInstanceOf(PostNotFound.class)
                .hasMessageContaining("존재하지");
    }
    @Test
    @DisplayName("글 삭제 예외 테스트")
    public void test10() {
        Post post = Post.builder()
                .title("bebe")
                .content("content")
                .build();

        postRepository.save(post);

        assertThatThrownBy(()-> postService.delete(post.getId()+1L))
                .isInstanceOf(PostNotFound.class)
                        .hasMessageContaining("존재하지");

    }
}