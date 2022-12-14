package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.config.data.UserSession;
import com.jacklog.jacklog.request.PostCreate;
import com.jacklog.jacklog.request.PostEdit;
import com.jacklog.jacklog.request.PostSearch;
import com.jacklog.jacklog.response.PostResponse;
import com.jacklog.jacklog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping("/test")
    public String test(){
        return "인증 필요 없음";
    }
    @GetMapping("/test2")
    public Long test2(UserSession userSession){
        return userSession.id;
    }
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate postCreate){
        postCreate.validate();
        postService.write(postCreate);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch){
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit){
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }
}
