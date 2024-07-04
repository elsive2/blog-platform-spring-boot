package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.dto.post.PostCreateDto;
import com.blog_platform.blog_platform_spring_boot.dto.post.PostUpdateDto;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import com.blog_platform.blog_platform_spring_boot.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    ResponseEntity<Page<PostResponse>> index(Pageable pageable) {
        return ResponseEntity.ok(postService.getAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<PostResponse> view(@PathVariable final long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<PostResponse> create(@RequestBody @Valid final PostCreateDto dto) {
        return ResponseEntity.ok(postService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @postService.isPostAuthor(#id, authentication.name)")
    ResponseEntity<PostResponse> update(@RequestBody @Valid final PostUpdateDto dto, @PathVariable final long id) {
        return ResponseEntity.ok(postService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @postService.isPostAuthor(#id, authentication.name)")
    ResponseEntity<String> delete(@PathVariable final long id) {
        postService.delete(id);

        return ResponseEntity.ok("deleted");
    }
}
