package com.blog_platform.blog_platform_spring_boot.mapper;

import com.blog_platform.blog_platform_spring_boot.entity.Community;
import com.blog_platform.blog_platform_spring_boot.response.CommunityResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommunityMapper implements AbstractMapper<Community, CommunityResponse> {
    private final UserMapper userMapper;

    @Override
    public CommunityResponse toResponse(Community entity) {
        CommunityResponse response = new CommunityResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setCreationDate(entity.getCreationDate());
        response.setAvatar(entity.getAvatar());
        response.setUser(userMapper.toResponse(entity.getUser()));
        return response;
    }
}
