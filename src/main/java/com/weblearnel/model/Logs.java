package com.weblearnel.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="log")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "datetime")
    private LocalDate dateTime;

    @Column(name = "user_id")
    private long user_id;

    @Column(name="role")
    private Integer role;

    @ManyToOne
    private User user;

}
