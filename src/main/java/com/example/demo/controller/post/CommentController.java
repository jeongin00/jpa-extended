package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.CommentCreateRequestDto;
import com.example.demo.controller.post.dto.CommentResponseDto;
import com.example.demo.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentController {
    CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentCreateRequestDto request) {
        CommentResponseDto comment = commentService.save(request);
        return ResponseEntity.ok(comment);
    }
}
