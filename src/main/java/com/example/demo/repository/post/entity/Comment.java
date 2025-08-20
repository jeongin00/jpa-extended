package com.example.demo.repository.post.entity;

import com.example.demo.repository.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
//  @Column(name = "post_id")
//  private Integer postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    private LocalDateTime updatedAt;

    public static Comment create(String content, Post post, User user) {
        return new Comment(
                null,
                content,
                post,
                user,
                LocalDateTime.now(),
                user,
                LocalDateTime.now()
        );
    }
}
