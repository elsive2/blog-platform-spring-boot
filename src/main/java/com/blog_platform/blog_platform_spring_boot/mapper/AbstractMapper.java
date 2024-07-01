package com.blog_platform.blog_platform_spring_boot.mapper;

import com.blog_platform.blog_platform_spring_boot.entity.AbstractEntity;
import com.blog_platform.blog_platform_spring_boot.response.AbstractResponse;
import org.springframework.stereotype.Component;

public interface AbstractMapper<E extends AbstractEntity, R extends AbstractResponse> {
    R toResponse(final E entity);
}
