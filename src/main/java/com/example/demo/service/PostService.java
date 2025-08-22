package com.example.demo.service;

import com.example.demo.controller.post.dto.PostCreateRequestDto;
import com.example.demo.controller.post.dto.PostResponseDto;
import com.example.demo.repository.post.PostRepository;
import com.example.demo.repository.post.entity.Post;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto findById(Integer id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("데이터베이스내 postId가 존재하지 않습니다."));
        return PostResponseDto.from(post);
    }

    @Transactional
    public List<PostResponseDto> findAll(){
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::from)
                .toList();
    }

    @Transactional
    public PostResponseDto save(PostCreateRequestDto request){
        // 작성자 User 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("데이터베이스 내 userid 없습니다."));

        // 작성한 post 저장
        Post post = Post.create(
                request.getTitle(),
                request.getContent(),
                user
        );


        Post created = postRepository.save(post);
        return PostResponseDto.from(created);
    }

    @Transactional
    public void delete(Integer id){
        Post post = postRepository.findById(id)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"유저가 데이터베이스 내 존재하지 않습니다."));
        postRepository.deleteById(id);
    }
}