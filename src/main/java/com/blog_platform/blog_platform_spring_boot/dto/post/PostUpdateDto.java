package com.blog_platform.blog_platform_spring_boot.dto.post;

import com.blog_platform.blog_platform_spring_boot.enums.PostStatusEnum;
import com.blog_platform.blog_platform_spring_boot.validation.EnumValueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostUpdateDto {
    @Length(max = 255, message = "title_length_is_wrong")
    private String title;

    @Length(max = 255, message = "title_description_is_wrong")
    private String description;

    private String content;

    @EnumValueConstraint(message = "status_is_invalid", enumClass = PostStatusEnum.class)
    private String status;
}
