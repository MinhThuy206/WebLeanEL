package com.weblearnel.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(name = "level_id")
    private long id;

    @Column(name = "name_lv")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private Set<Exam> exams;

    // @OneToMany
    // private ArrayList<Question> questions;

    // @OneToMany
    // private ArrayList<Question> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private Set<Question> questions;
}