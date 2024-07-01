package com.blog_platform.blog_platform_spring_boot.repository;

import com.blog_platform.blog_platform_spring_boot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
