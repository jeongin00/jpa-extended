package com.example.demo.controller.post.dto;

import com.example.demo.controller.user.dto.UserSimpleResponseDto;
import com.example.demo.repository.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Integer id;
    private String name;
    private String content;
    private List<CommentResponseDto> comments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private UserSimpleResponseDto createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private UserSimpleResponseDto updatedBy;

    public static PostResponseDto from(Post entity) {
        return new PostResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getComments().stream().map(CommentResponseDto::from).toList(),
                entity.getCreatedAt(),
                UserSimpleResponseDto.from(entity.getCreatedBy()),
                entity.getUpdatedAt(),
                UserSimpleResponseDto.from(entity.getUpdatedBy())
        );
    }
}
