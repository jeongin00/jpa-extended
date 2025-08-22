package com.example.demo.controller.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {
    @NotBlank
    @Size(min = 10, max = 20, message = "댓글은 최소 10자, 최대 20자")
    private String content;  // 댓글 내용
    @NotNull
    private Integer postId;  // 어떤 글에 달린 댓글인지
    @NotNull
    private Integer userId;  // 작성자
}


