package com.weblearnel.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_id")
    private long topicId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private Set<ExamTopic> examTopic;

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private Set<Question> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private Set<Word> words;

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private Set<TopicPassed> topicPassed;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_topic", 
        joinColumns = @JoinColumn(name = "tp_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();
}