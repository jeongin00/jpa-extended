package com.example.demo.repository.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uq_allocated_group_user", columnNames = {"group_id", "user_id"})
})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Allocated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime allocatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public static Allocated create(Group group, User user) {
        return new Allocated(
                null,
                LocalDateTime.now(),
                group,
                user
        );
    }
}
