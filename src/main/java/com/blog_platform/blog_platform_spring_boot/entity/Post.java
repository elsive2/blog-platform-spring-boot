package com.blog_platform.blog_platform_spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;
}
