package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.*;
import com.example.demo.service.GroupService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupController {
    GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> group(@PathVariable Integer id) {
        GroupResponseDto group = groupService.findById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("")
    public ResponseEntity<List<GroupResponseDto>> groups() {
        List<GroupResponseDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("")
    public ResponseEntity<GroupResponseDto> create(@RequestBody @Valid GroupCreateRequestDto request) {
        GroupResponseDto group = groupService.save(request);
        return ResponseEntity.ok(group);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> update(@PathVariable Integer id, @RequestBody GroupUpdateRequestDto request) {
        GroupResponseDto group = groupService.update(id, request);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        groupService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
