package com.example.demo.service;

import com.example.demo.controller.post.dto.CommentCreateRequestDto;
import com.example.demo.controller.post.dto.CommentResponseDto;
import com.example.demo.repository.post.CommentRepository;
import com.example.demo.repository.post.PostRepository;
import com.example.demo.repository.post.entity.Comment;
import com.example.demo.repository.post.entity.Post;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor  // final 필드들로 생성자 자동 생성
public class CommentService{
    private final CommentRepository commentRepository;   // required
    private final PostRepository postRepository;    // required
    private final UserRepository userRepository;    // required

    @Transactional
    public CommentResponseDto save(@Valid CommentCreateRequestDto request){
        // 원본글 Post 조회
        Integer postId = request.getPostId();
        Post post = postRepository.findById(postId)  // userRepository.findById(userId)는 Optional<T> 를 돌려줌
                .orElseThrow(() -> new RuntimeException("포스트가 데이터베이스 내 존재하지 않습니다. 포스트 id : " + postId));
                // post 조회 실패 -> 예외 발생 -> 메서드 중단
        // 작성자 User 조회
        Integer userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + userId));

        // 작성한 Comment 저장
        Comment comment = Comment.create(
                request.getContent(),
                post,
                user
        );
        Comment created = commentRepository.save(comment);
        return CommentResponseDto.from(created);
    }  // 여기서 COMMIT or ROLLBACK

    @Transactional
    public void delete(Integer postId, Integer commentId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("포스트가 데이터베이스 내 존재하지 않습니다. 포스트 id : " + postId));
        Comment found = post.getComments().stream()
                .filter((comment) -> commentId.equals(comment.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("포스트 내 해당 댓글이 존재하지 않습니다. 포스트 id : " + postId + " - 댓글 id: " + commentId));
        post.getComments().remove(found);
    }
}