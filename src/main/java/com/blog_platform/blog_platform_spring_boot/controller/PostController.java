package com.blog_platform.blog_platform_spring_boot.controller;

import com.blog_platform.blog_platform_spring_boot.dto.post.PostCreateDto;
import com.blog_platform.blog_platform_spring_boot.dto.post.PostUpdateDto;
import com.blog_platform.blog_platform_spring_boot.response.PostResponse;
import com.blog_platform.blog_platform_spring_boot.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    ResponseEntity<List<PostResponse>> index() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<PostResponse> view(@PathVariable final long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<PostResponse> create(@RequestBody final PostCreateDto dto) {
        return ResponseEntity.ok(postService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @postService.isPostAuthor(#id, authentication.name)")
    ResponseEntity<PostResponse> update(@RequestBody final PostUpdateDto dto, @PathVariable final long id) {
        return ResponseEntity.ok(postService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @postService.isPostAuthor(#id, authentication.name)")
    ResponseEntity<String> delete(@PathVariable final long id) {
        postService.delete(id);

        return ResponseEntity.ok("deleted");
    }
}
