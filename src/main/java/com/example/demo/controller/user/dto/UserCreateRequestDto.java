package com.example.demo.controller.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {
    @NotBlank(message = "user")
    private String username;
    @NotBlank(message = "user")
    @Size(max = 10, message = "비밀번호는 최대 10자리까지 가능합니다.")
    private String password;
    @NotBlank(message = "user")
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    private String job;
    @NotBlank
    private String specialty;
}