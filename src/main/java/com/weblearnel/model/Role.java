package com.weblearnel.model;

import com.weblearnel.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
