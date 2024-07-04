package com.blog_platform.blog_platform_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "unauthorized")
public class UnauthorizedException extends RuntimeException {
}
