package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.dto.community.CommunityCreateDto;
import com.blog_platform.blog_platform_spring_boot.dto.community.CommunityUpdateDto;
import com.blog_platform.blog_platform_spring_boot.entity.Community;
import com.blog_platform.blog_platform_spring_boot.entity.User;
import com.blog_platform.blog_platform_spring_boot.exception.CommunityNotFoundException;
import com.blog_platform.blog_platform_spring_boot.mapper.CommunityMapper;
import com.blog_platform.blog_platform_spring_boot.repository.CommunityRepository;
import com.blog_platform.blog_platform_spring_boot.response.CommunityResponse;
import com.blog_platform.blog_platform_spring_boot.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityMapper communityMapper;

    public Page<CommunityResponse> getAll(final Pageable pageable) {
        return communityRepository.findAll(pageable).map(communityMapper::toResponse);
    }

    public CommunityResponse getById(final UUID id) {
        final Community community = communityRepository.findById(id)
            .orElseThrow(CommunityNotFoundException::new);

        return communityMapper.toResponse(community);
    }

    public CommunityResponse create(final CommunityCreateDto dto) {
        final User user = UserUtils.getCurrentUser();

        Community community = new Community();
        community.setTitle(dto.getTitle());
        community.setDescription(dto.getDescription());
        community.setAvatar("avatar.png");
        community.setUser(user);

        communityRepository.save(community);

        return communityMapper.toResponse(community);
    }

    @Transactional
    public CommunityResponse update(final UUID id, final CommunityUpdateDto dto) {
        final Community community = communityRepository.findById(id)
            .orElseThrow(CommunityNotFoundException::new);

        community.setTitle(dto.getTitle() != null ? dto.getTitle() : community.getTitle());
        community.setDescription(dto.getDescription() != null ? dto.getDescription() : community.getDescription());

        communityRepository.save(community);

        return communityMapper.toResponse(community);
    }

    @Transactional
    public void delete(final UUID id) {
        final Community community = communityRepository.findById(id)
            .orElseThrow(CommunityNotFoundException::new);

        communityRepository.delete(community);
    }
}
