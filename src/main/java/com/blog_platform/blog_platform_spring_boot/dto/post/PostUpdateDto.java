package com.blog_platform.blog_platform_spring_boot.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateDto {
    private String title;

    private String description;

    private String content;
}
