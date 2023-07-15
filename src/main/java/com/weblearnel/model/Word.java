package com.weblearnel.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

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

    @Column(name = "topic_id")
    private Integer topic_id;

    @ManyToOne
    private Topic topic;

    @OneToMany
    private ArrayList<Result> results;
}
