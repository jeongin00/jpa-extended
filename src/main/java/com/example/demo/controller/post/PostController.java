package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.PostCreateRequestDto;
import com.example.demo.controller.post.dto.PostResponseDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {
    PostService postService;
    CommentService commentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostResponseDto> post(@PathVariable Integer id) {
        PostResponseDto post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostResponseDto>> posts() {
        List<PostResponseDto> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> create( @RequestBody @Valid  PostCreateRequestDto request) {
        PostResponseDto post = postService.save(request);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Integer postId, @PathVariable Integer commentId) {
        commentService.delete(postId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
