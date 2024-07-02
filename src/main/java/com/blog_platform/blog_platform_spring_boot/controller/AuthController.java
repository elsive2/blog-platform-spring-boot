package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.dto.user.LoginUserDto;
import com.blog_platform.blog_platform_spring_boot.dto.user.RegisterUserDto;
import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.response.LoginResponse;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import com.blog_platform.blog_platform_spring_boot.service.AuthService;
import com.blog_platform.blog_platform_spring_boot.utils.JwtTokenUtils;
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
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        UserResponse response = authService.signUp(registerUserDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.signIn(loginUserDto);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtTokenUtil.generateToken(authenticatedUser));
        loginResponse.setExpirationTime(jwtTokenUtil.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
