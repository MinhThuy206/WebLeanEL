package com.weblearnel.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
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
    private int type;
    

    public Question(String content,  String option1, String option2, String option3, String option4, String answer,
            String explanation, int type2) {
        this.content = content;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.explanation = explanation;
        this.type = type2;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_topic_id", referencedColumnName = "tp_id")
    private Topic topic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_level_id", referencedColumnName = "level_id")
    private Level level;

    public void assignLevel(Level levelToAssign) {
        this.level = levelToAssign;
        // levelToAssign.getQuestions().add(this);
    }

    public void assignTopic(Topic topicToAssign) {
        this.topic = topicToAssign;
        // topicToAssign.getQuestions().add(this);
    }

    public Question(String content2, String option12, String option22, String option32, String option42, int answer2,
            String explain, int type2) {
    }
}