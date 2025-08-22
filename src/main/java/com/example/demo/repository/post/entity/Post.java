package com.example.demo.repository.post.entity;

import com.example.demo.repository.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.example.demo.repository.user.entity.QUser.user;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    private LocalDateTime updatedAt;

    public static Post create(String title, String content, User user){
        return new Post(
                null,
                title,
                content,
                Collections.emptyList(),
                user,
                LocalDateTime.now(),
                user,
                LocalDateTime.now()
        );
    }
}