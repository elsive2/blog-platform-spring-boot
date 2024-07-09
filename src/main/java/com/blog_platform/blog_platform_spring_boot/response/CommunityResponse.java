package com.blog_platform.blog_platform_spring_boot.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommunityResponse implements AbstractResponse {
    private UUID id;
    private String title;
    private String description;
    private String avatar;
    private LocalDateTime creationDate;
    private UserResponse user;
}
