package com.blog_platform.blog_platform_spring_boot.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;

    private long expirationTime;
}
