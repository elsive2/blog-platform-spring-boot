package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.dto.user.LoginUserDto;
import com.blog_platform.blog_platform_spring_boot.dto.user.RegisterUserDto;
import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.mapper.UserMapper;
import com.blog_platform.blog_platform_spring_boot.repository.UserRepository;
import com.blog_platform.blog_platform_spring_boot.response.LoginResponse;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import com.blog_platform.blog_platform_spring_boot.utils.JwtTokenUtils;
import com.blog_platform.blog_platform_spring_boot.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtTokenUtils jwtTokenUtils;

    public UserResponse signUp(final RegisterUserDto dto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRefreshHashCode(StringUtils.generateRandomString(32));

        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public LoginResponse signIn(final LoginUserDto dto) {
        authenticationManager.authenticate(
                (new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                ))
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRefreshHashCode(StringUtils.generateRandomString(32));
        userRepository.save(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwtTokenUtils.generateAccessToken(user));
        loginResponse.setRefreshToken(jwtTokenUtils.generateRefreshToken(user.getRefreshHashCode(), user));
        return loginResponse;
    }

    public LoginResponse refresh(final String token) {
        if (!jwtTokenUtils.isTokenValid(token)) {
            throw new RuntimeException("Refresh token expired");
        }

        final String refreshHashCode = jwtTokenUtils.extractClaim(
            token, claim -> claim.get("refreshHashCode", String.class)
        );
        final String username = jwtTokenUtils.extractUsername(token);

        final Optional<User> optionalUser = userRepository.findByRefreshHashCodeAndUsername(refreshHashCode, username);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        user.setRefreshHashCode(StringUtils.generateRandomString(32));

        userRepository.save(user);

        LoginResponse response = new LoginResponse();
        response.setRefreshToken(jwtTokenUtils.generateRefreshToken(user.getRefreshHashCode(), user));
        response.setAccessToken(jwtTokenUtils.generateAccessToken(user));
        return response;
    }
}
