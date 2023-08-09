package com.weblearnel.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tp_id", referencedColumnName = "tp_id")
    private Topic topic;
    
    public Word(String name, String mean, String attribute, String example, String imageUrl, String pronounce) {
        this.name = name;
        this.pronounce = pronounce;
        this.imageUrl = imageUrl;
        this.mean = mean;
        this.attribute = attribute;
        this.example = example;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "word")
    private Set<Result> results;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_level_id", referencedColumnName = "level_id")
    private Level level;

    public void assignTopic(Topic topicToAssign) {
        this.topic = topicToAssign;
        // topicToAssign.getWords().add(this);
    }

    public void assignLevel(Level levelToAssign) {
        this.level = levelToAssign;
        // levelToAssign.getQuestions().add(this);
    }
}
