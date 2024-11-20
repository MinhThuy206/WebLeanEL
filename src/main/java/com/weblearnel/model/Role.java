package com.weblearnel.model;

import com.weblearnel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
