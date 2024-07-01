package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.entity.Post;
import com.blog_platform.blog_platform_spring_boot.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    List<Post> index() {
        return postRepository.findAll();
    }
}
