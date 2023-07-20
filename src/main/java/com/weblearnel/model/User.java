package com.weblearnel.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String name;

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

    private Role getERole() {
        return Role.getRole(this.role);
    }

    @Column(name = "role")
    private Integer role;

    private void setERole(Role role) {
        this.setRole(role.getValue());
    }

    // @OneToMany
    // private ArrayList<Logs> logs;

    // @OneToMany(mappedBy = "user")
    // private ArrayList<Result> results;
}