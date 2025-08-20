package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import com.example.demo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users(@RequestParam(required = false) String username) {
        List<UserResponseDto> users = Optional.ofNullable(username)
                .map(userService::findByUsername)
                .orElseGet(userService::findAll);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
        UserResponseDto user = userService.save(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
