package com.example.demo.repository.user;

import com.example.demo.repository.user.entity.User;

import java.util.List;

public interface IUserRepositoryCustom {
    List<User> findByName(String name);
}
