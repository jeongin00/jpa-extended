package com.example.demo.repository.post;

import com.example.demo.repository.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Integer id);

    List<Post> findAll();

    Post save(Post entity);

    void deleteById(Integer id);
}
