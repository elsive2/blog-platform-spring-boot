package com.blog_platform.blog_platform_spring_boot.utils;

import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException();
        }
        return (User) authentication.getPrincipal();
    }
}
