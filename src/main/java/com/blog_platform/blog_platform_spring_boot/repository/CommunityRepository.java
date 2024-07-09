package com.blog_platform.blog_platform_spring_boot.repository;

import com.blog_platform.blog_platform_spring_boot.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, UUID> {
}
