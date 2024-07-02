package com.blog_platform.blog_platform_spring_boot.service;

import com.blog_platform.blog_platform_spring_boot.dto.post.PostCreateDto;
import com.blog_platform.blog_platform_spring_boot.dto.post.PostUpdateDto;
import com.blog_platform.blog_platform_spring_boot.entity.Post;
import com.blog_platform.blog_platform_spring_boot.mapper.PostMapper;
import com.blog_platform.blog_platform_spring_boot.repository.PostRepository;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import com.blog_platform.blog_platform_spring_boot.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponse)
                .toList();
    }

    public PostResponse getById(final long id) {
        final Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new RuntimeException("Post not found");
        }

        return postMapper.toResponse(post.get());
    }

    public PostResponse create(final PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        post.setUser(UserUtils.getCurrentUser());
        post.setStatus(dto.getStatus());

        postRepository.save(post);

        return postMapper.toResponse(post);
    }

    @Transactional
    public PostResponse update(final PostUpdateDto dto, final long id) {
        final Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isEmpty()) {
            throw new RuntimeException("Post not found");
        }

        Post post = postOptional.get();

        post.setContent(dto.getContent() != null ? dto.getContent() : post.getContent());
        post.setTitle(dto.getTitle() != null ? dto.getTitle() : post.getTitle());
        post.setDescription(dto.getDescription() != null ? dto.getDescription() : post.getDescription());

        return postMapper.toResponse(post);
    }

    @Transactional
    public void delete(final long id) {
        final Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isEmpty()) {
            throw new RuntimeException("Post not found");
        }

        postRepository.delete(postOptional.get());
    }
}
