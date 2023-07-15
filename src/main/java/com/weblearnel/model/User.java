package com.weblearnel.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "created_date")
    private Date createDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "level")
    private Integer level;

    private Role getERole(){
        return Role.getRole(this.role);
    }

    @Column(name="role")
    private Integer role;

    private void setERole(Role role){
        this.setRole(role.getValue());
    }

    @OneToMany
    private ArrayList<Logs> logs;

    @OneToMany
    private ArrayList<Result> results;
}

