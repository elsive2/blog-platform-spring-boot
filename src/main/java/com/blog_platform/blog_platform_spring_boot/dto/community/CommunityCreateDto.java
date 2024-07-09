package com.blog_platform.blog_platform_spring_boot.dto.community;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommunityCreateDto {
    @NotBlank(message = "title_is_mandatory")
    @Length(max = 255, message = "title_length_wrong")
    private String title;

    @NotBlank(message = "description_is_mandatory")
    @Length(max = 255, message = "description_length_wrong")
    private String description;
}
