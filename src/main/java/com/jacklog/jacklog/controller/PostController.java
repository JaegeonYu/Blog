package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PostController {
    @PostMapping("/posts")
    public String post(@RequestBody PostCreate postCreate){
        log.info("title={}, content={}",postCreate.getTitle(), postCreate.getContent());
        return "hello world";
    }

}
