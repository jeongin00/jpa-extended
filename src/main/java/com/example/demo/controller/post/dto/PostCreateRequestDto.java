package com.example.demo.controller.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    @Size(max = 20, message ="제목은 최대 20자입니다.")
    private String title;

    @NotBlank(message = "내용은 비어있을 수 없습니다.")
    @Size(min = 10 , message = "내용은 최소 10자입니다.")
    private String content;

    @NotNull(message = "userId는 필수입니다.")
    private Integer userId;
}
