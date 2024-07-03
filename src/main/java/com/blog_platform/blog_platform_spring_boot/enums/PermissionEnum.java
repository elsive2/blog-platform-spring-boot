package com.blog_platform.blog_platform_spring_boot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionEnum {
    POST_READ("post:read"),
    POST_CREATE("post:create"),
    POST_UPDATE("post:update"),
    POST_DELETE("post:delete");

    private final String permissionName;
}
