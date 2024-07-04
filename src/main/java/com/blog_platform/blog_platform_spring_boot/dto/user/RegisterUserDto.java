package com.blog_platform.blog_platform_spring_boot.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    @NotBlank(message = "username_is_mandatory")
    private String username;

    @NotBlank(message = "password_is_mandatory")
    private String password;

    @NotBlank(message = "email_is_mandatory")
    @Email(message = "email_is_invalid")
    private String email;
}
