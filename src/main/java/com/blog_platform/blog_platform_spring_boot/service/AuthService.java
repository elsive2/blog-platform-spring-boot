package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.dto.user.LoginUserDto;
import com.blog_platform.blog_platform_spring_boot.dto.user.RegisterUserDto;
import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.mapper.UserMapper;
import com.blog_platform.blog_platform_spring_boot.repository.UserRepository;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserResponse signUp(final RegisterUserDto dto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public User signIn(final LoginUserDto dto) {
        authenticationManager.authenticate(
                (new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                ))
        );

        return userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
