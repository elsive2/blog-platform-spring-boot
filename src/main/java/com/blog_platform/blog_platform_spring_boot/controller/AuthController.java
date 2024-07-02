package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.dto.LoginUserDto;
import com.blog_platform.blog_platform_spring_boot.dto.RegisterUserDto;
import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.response.LoginResponse;
import com.blog_platform.blog_platform_spring_boot.response.UserResponse;
import com.blog_platform.blog_platform_spring_boot.service.AuthService;
import com.blog_platform.blog_platform_spring_boot.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final JwtTokenUtils jwtTokenUtil;

    private final AuthService authService;

    public AuthController(JwtTokenUtils jwtTokenUtil, AuthService authService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authService = authService;
    }

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
