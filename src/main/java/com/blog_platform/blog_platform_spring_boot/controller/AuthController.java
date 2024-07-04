package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.dto.user.LoginUserDto;
import com.blog_platform.blog_platform_spring_boot.dto.user.RefreshTokenDto;
import com.blog_platform.blog_platform_spring_boot.dto.user.RegisterUserDto;
import com.blog_platform.blog_platform_spring_boot.response.LoginResponse;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import com.blog_platform.blog_platform_spring_boot.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        final UserResponse response = authService.signUp(registerUserDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        final LoginResponse response = authService.signIn(loginUserDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenDto refreshTokenDto) {
        final LoginResponse response = authService.refresh(refreshTokenDto.getToken());

        return ResponseEntity.ok(response);
    }
}
