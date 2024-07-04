package com.blog_platform.blog_platform_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "such_user_exists")
public class SuchUserAlreadyExistsException extends RuntimeException {
}
