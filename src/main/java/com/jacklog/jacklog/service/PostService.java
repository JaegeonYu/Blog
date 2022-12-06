package com.jacklog.jacklog.service;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.message.ErrorMessage;
import com.jacklog.jacklog.repository.PostRepository;
import com.jacklog.jacklog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.jacklog.jacklog.message.ErrorMessage.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent()).build();
        postRepository.save(post);
    }

    public Post get(Long id){
        return postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(POST_NOT_EXIST.getMessage()));
    }
}
