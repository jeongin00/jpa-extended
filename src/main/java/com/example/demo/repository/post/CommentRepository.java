package com.example.demo.repository.post;

import com.example.demo.repository.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findById(Integer id);

    Comment save(Comment entity);

    void deleteById(Integer id);
}
