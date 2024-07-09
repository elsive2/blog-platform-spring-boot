package com.blog_platform.blog_platform_spring_boot.entity;

import com.blog_platform.blog_platform_spring_boot.enums.PostStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post implements AbstractEntity {

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

    @Column(name = "publication_date", nullable = false, updatable = false)
    private LocalDateTime publicationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PostStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "community_post",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private Set<Community> communities;

    @PrePersist
    protected void onCreate() {
        publicationDate = LocalDateTime.now();
    }

}
