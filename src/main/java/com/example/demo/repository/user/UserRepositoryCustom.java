package com.example.demo.repository.user;

import com.example.demo.repository.user.entity.QUser;
import com.example.demo.repository.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustom implements IUserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findByName(String name) {
        return jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.name.trim().eq(name))
                .fetch();
    }
}
