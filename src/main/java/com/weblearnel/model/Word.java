package com.weblearnel.model;

import java.util.ArrayList;

import jakarta.persistence.*;
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
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pronounce")
    private String pronounce;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "mean")
    private String mean;

    @Column(name = "attributes")
    private String attribute;

    @Column(name = "example")
    private String example;


    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @OneToMany
    private ArrayList<Result> results;
}
