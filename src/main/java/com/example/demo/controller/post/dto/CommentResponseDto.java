package com.example.demo.controller.post.dto;

import com.example.demo.repository.post.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Integer id;
    private String content;

    public static CommentResponseDto from(Comment entity) {
        return new CommentResponseDto(
                entity.getId(),
                entity.getContent()
        );
    }
}
