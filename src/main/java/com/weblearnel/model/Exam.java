package com.weblearnel.model;

import java.time.LocalDateTime;

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
    int examId;

    @Column(name = "ex_question_no")
    int examQuestionNo;

    @Column(name = "ex_time", columnDefinition = "DATETIME")
    LocalDateTime  examTime;

    @Column(name = "lv_id")
    int levelId;

}
