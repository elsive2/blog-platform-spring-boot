package com.blog_platform.blog_platform_spring_boot.mapper;

import com.blog_platform.blog_platform_spring_boot.entity.AbstractEntity;
import com.blog_platform.blog_platform_spring_boot.entity.Post;
import com.blog_platform.blog_platform_spring_boot.response.AbstractResponse;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements AbstractMapper<Post, PostResponse> {
    @Override
    public PostResponse toResponse(Post entity) {
        PostResponse response = new PostResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setDescription(entity.getDescription());

        return response;
    }
}
