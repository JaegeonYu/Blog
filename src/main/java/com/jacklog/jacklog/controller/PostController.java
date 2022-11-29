package com.jacklog.jacklog.controller;

import com.jacklog.jacklog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PostController {
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate postCreate){
        return Map.of();
    }

}
