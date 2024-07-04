package com.blog_platform.blog_platform_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user_not_found")
public class UserNotFoundException extends RuntimeException {
}
