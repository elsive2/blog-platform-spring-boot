package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.entity.Post;
import com.blog_platform.blog_platform_spring_boot.mapper.PostMapper;
import com.blog_platform.blog_platform_spring_boot.repository.PostRepository;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostService(
        final PostRepository postRepository,
        final PostMapper postMapper
    ) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponse)
                .toList();
    }
}
