package com.weblearnel.model;

import java.time.LocalDateTime;
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
    @Column(name = "ex_id")
    private long examId;

    @Column(name = "ex_question_no")
    private int examQuestionNo;

    @Column(name = "ex_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime  examTime;

    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    private Set<ExamTopic> examTopics;

    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    private Set<Result> results;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_lv_id", referencedColumnName = "level_id") // id = id in the exam table
    private Level level;
}
