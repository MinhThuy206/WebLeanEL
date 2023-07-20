package com.weblearnel.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "log")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String name;

    @Column(name = "status")
    private int status;

    @Column(name = "datetime")
    private LocalDate dateTime;

    @Column(name = "role")
    private int role;

    // @ManyToOne
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    // private User user;

}