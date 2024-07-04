package com.blog_platform.blog_platform_spring_boot.dto.post;

import com.blog_platform.blog_platform_spring_boot.enums.PostStatusEnum;
import com.blog_platform.blog_platform_spring_boot.validation.EnumValueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateDto {
    @NotBlank(message = "title_is_mandatory")
    @Length(max = 255, message = "title_length_is_wrong")
    private String title;

    @NotBlank(message = "description_is_mandatory")
    @Length(max = 255, message = "title_description_is_wrong")
    private String description;

    @NotBlank(message = "content_is_mandatory")
    private String content;

    @EnumValueConstraint(message = "status_is_invalid", enumClass = PostStatusEnum.class)
    private String status;
}
