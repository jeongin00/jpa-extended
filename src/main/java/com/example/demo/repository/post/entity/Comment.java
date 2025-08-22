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
    @Id // 이 필드를 PK로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT 방식 사용
    private Integer id;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER) // Comment를 DB에서 가져올 때, 연관된 Post/User도 같이 즉시 조회
    @JoinColumn(name = "post_id")  // comment 테이블에 post_id 컬럼이 FK로 생김
    private Post post;  // 이 댓글이 달린 게시글 객체 이를 통해 어떤 엔티티랑 연관됐는지 알 수 있음
//  @Column(name = "post_id")
//  private Integer postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;
    private LocalDateTime createdAt;   // 생성시점

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    private LocalDateTime updatedAt;  // 수정시점

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