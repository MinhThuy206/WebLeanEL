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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long question_id;

    @Column(name = "content")
    private String content;

    @Column(name = "answer")
    private String answer;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "type")
    private String type;

    // @ManyToOne
    // @JoinColumn(name = "fk_topic_id", referencedColumnName = "topic_id")
    // private Topic topics;

    // @ManyToOne
    // @JoinColumn(name = "fk_level_id", referencedColumnName = "level_id")
    // private Level level;
}