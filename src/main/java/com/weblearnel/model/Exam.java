package com.weblearnel.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ex_question_no")
    private int examQuestionNo;

    @Column(name = "ex_time", columnDefinition = "DATETIME")
    private LocalDateTime  examTime;


    @OneToMany
    private ArrayList<ExamTopic> examTopics;

    @OneToMany
    private ArrayList<Result> results;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;
}
