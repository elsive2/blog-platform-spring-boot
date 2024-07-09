package com.blog_platform.blog_platform_spring_boot.dto.community;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommunityUpdateDto {
    @Length(max = 255, message = "title_length_wrong")
    private String title;

    @Length(max = 255, message = "description_length_wrong")
    private String description;
}
