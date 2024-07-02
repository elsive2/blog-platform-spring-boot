package com.blog_platform.blog_platform_spring_boot.mapper;

import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements AbstractMapper<User, UserResponse> {
    @Override
    public UserResponse toResponse(User entity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(entity.getId());
        userResponse.setUsername(entity.getUsername());
        return userResponse;
    }
}
