package com.blog_platform.blog_platform_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "refresh_token_expired")
public class RefreshTokenExpiredException extends RuntimeException {
}
