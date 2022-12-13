package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.request.PostCreate;
import com.jacklog.jacklog.response.PostResponse;
import com.jacklog.jacklog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate postCreate){
       postService.write(postCreate);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(){
        return postService.getList(1);
    }

}
