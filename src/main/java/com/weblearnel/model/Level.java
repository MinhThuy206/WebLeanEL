package com.weblearnel.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    private ArrayList<Exam> exams;

    @OneToMany
    private ArrayList<Question> questions;
}
