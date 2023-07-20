package com.weblearnel.model;

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
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
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

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fk_topic_id", referencedColumnName = "topic_id")
    // private Topic topic;

    // @OneToMany
    // private ArrayList<Result> results;
}