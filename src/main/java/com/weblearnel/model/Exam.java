package com.weblearnel.model;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "ex_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examId;

    @Column(name = "ex_question_no")
    private int examQuestionNo;

    @Column(name = "ex_time", columnDefinition = "DATETIME")
    private LocalDateTime  examTime;

    @Column(name = "lv_id")
    private int levelId;

    @OneToMany(mappedBy = "examId")
    private List<ExamTopic> examTopics;


}
