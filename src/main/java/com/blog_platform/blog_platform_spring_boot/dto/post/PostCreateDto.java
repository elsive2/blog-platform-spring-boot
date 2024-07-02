package com.blog_platform.blog_platform_spring_boot.dto.post;

import com.blog_platform.blog_platform_spring_boot.enums.PostStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDto {
    private String title;

    private String description;

    private String content;

    private PostStatusEnum status;
}
