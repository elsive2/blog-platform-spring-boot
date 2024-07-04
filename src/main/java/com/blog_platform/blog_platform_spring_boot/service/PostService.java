package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.dto.post.PostCreateDto;
import com.blog_platform.blog_platform_spring_boot.dto.post.PostUpdateDto;
import com.blog_platform.blog_platform_spring_boot.entity.Post;
import com.blog_platform.blog_platform_spring_boot.enums.PostStatusEnum;
import com.blog_platform.blog_platform_spring_boot.exception.PostNotFoundException;
import com.blog_platform.blog_platform_spring_boot.mapper.PostMapper;
import com.blog_platform.blog_platform_spring_boot.repository.PostRepository;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import com.blog_platform.blog_platform_spring_boot.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Page<PostResponse> getAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toResponse);
    }

    public PostResponse getById(final long id) {
        final Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        return postMapper.toResponse(post);
    }

    public PostResponse create(final PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        post.setUser(UserUtils.getCurrentUser());
        post.setStatus(dto.getStatus() != null ? PostStatusEnum.valueOf(dto.getStatus()) : PostStatusEnum.PUBLISHED);

        postRepository.save(post);

        return postMapper.toResponse(post);
    }

    @Transactional
    public PostResponse update(final PostUpdateDto dto, final long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        post.setContent(dto.getContent() != null ? dto.getContent() : post.getContent());
        post.setTitle(dto.getTitle() != null ? dto.getTitle() : post.getTitle());
        post.setDescription(dto.getDescription() != null ? dto.getDescription() : post.getDescription());
        post.setStatus(dto.getStatus() != null ? PostStatusEnum.valueOf(dto.getStatus()) : post.getStatus());

        return postMapper.toResponse(post);
    }

    @Transactional
    public void delete(final long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
    }

    public boolean isPostAuthor(final long id, final String username) {
        return postRepository.findByIdAndUserUsername(id, username).isPresent();
    }
}
