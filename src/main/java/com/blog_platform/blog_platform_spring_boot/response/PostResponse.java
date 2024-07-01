package com.blog_platform.blog_platform_spring_boot.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse implements AbstractResponse {
    private Long id;
    private String title;
    private String description;
    private String content;
}
