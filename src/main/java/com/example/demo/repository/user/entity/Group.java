package com.example.demo.repository.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String desc;
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<Allocated> allocates;

    public static Group create(String name, String desc) {
        return new Group(
                null,
                name,
                desc,
                LocalDateTime.now(),
                Collections.emptyList()
        );
    }

    public void update(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
