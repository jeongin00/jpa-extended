package com.example.demo.controller.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GroupUpdateRequestDto {
    @NotNull(message = "이름은 비울 수 없습니다 입력해주세요.")
    private String name;
    @NotBlank(message = "그룹설명은 빈값이 될 수 없습니다. 최소 한글자를 입력하세요")
    private String desc;

    // 비워놔도 된다는 전재하에
    private List<Integer> userIds;
}