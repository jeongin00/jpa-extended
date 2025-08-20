package com.example.demo.repository.user;

import com.example.demo.repository.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, IUserRepositoryCustom {

    Optional<User> findById(Integer id);

    List<User> findAll();

    User save(User entity);

    void deleteById(Integer id);
}