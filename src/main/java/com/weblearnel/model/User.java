package com.weblearnel.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private Date createDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "level")
    private Integer level;

    @Column(name = "enabled")
    Boolean enabled = false;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    private String token;

    private Role getERole() {
        return Role.getRole(this.role);
    }

    @Column(name = "role")
    private Integer role;

    private void setERole(Role role) {
        this.setRole(role.getValue());
    }

    public User(String username, String password, String fullname, String address, String mobile, String email,
            Integer level) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.level = level;
    }

    public LocalDateTime getTokenCreationDate() {
        return tokenCreationDate;
    }

    public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
        this.tokenCreationDate = tokenCreationDate;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Result> results;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    // @JoinTable(name = "topic_passed")
    private Set<TopicPassed> topicsPassed = new HashSet<>();
}
