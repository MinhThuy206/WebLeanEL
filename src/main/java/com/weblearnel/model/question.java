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
public class question {
    @Id
    @Column(name = "qu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int question_id;
    @Column(name = "qu_content")
    private String qu_content;
    @Column(name = "qu_answer")
    private String qu_answer;
    @Column(name = "qu_option1")
    private String qu_option1;
    @Column(name = "qu_option2")
    private String qu_option2;
    @Column(name = "qu_option3")
    private String qu_option3;
    @Column(name = "qu_option4")
    private String qu_option4;
    @Column(name = "qu_explain")
    private String qu_explain;
    @Column(name = "qu_type")
    private String qu_type;
    @Column(name = "id_topic")
    private int id_topic;
    @Column(name = "id_level")
    private int id_level;
}
