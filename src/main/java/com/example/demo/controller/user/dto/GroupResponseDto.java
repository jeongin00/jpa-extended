package com.example.demo.controller.user.dto;

import com.example.demo.repository.user.entity.Allocated;
import com.example.demo.repository.user.entity.Group;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupResponseDto {
    private Integer id;
    private String name;
    private String desc;
    private List<UserSimpleResponseDto> users;

    public static GroupResponseDto from(Group entity) {
        return new GroupResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDesc(),
                entity.getAllocates().stream()
                        .map(Allocated::getUser)
                        .map(UserSimpleResponseDto::from)
                        .toList()
        );
    }
}