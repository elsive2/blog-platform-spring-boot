package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.mapper.UserMapper;
import com.blog_platform.blog_platform_spring_boot.repository.UserRepository;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserResponse> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    public UserResponse getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return mapper.toResponse(currentUser);
    }
}
