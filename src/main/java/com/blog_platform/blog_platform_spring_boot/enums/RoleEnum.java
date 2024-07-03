package com.blog_platform.blog_platform_spring_boot.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.blog_platform.blog_platform_spring_boot.enums.PermissionEnum.*;

@RequiredArgsConstructor
public enum RoleEnum {
    USER(
        Set.of(
            POST_READ
        )
    ),
    MODERATOR(
        Set.of(
            POST_READ,
            POST_CREATE
        )
    ),
    ADMIN(
        Set.of(
            POST_READ,
            POST_CREATE,
            POST_UPDATE,
            POST_DELETE
        )
    );

    private final Set<PermissionEnum> permissions;

    public final List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> perms = permissions.stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
            .collect(Collectors.toList());

        perms.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return perms;
    }
}
