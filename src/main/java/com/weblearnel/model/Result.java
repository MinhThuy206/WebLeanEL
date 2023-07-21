package com.weblearnel.model;

import java.time.LocalDateTime;

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

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_id")
    private long resultid;

    @Column(name = "score")
    private int Score;

    @Column(name = "datetime", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime resultDatetime;

    @Column(name = "rs_flag")
    private int resultFlag;

    @Column(name = "rs_type")
    private int resultType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_word_id", referencedColumnName = "word_id") // id = id in the exam table
    private Word word;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ex_id", referencedColumnName = "ex_id") // id = id in the exam table
    private Exam exam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user_id", referencedColumnName = "user_id") // id = id in the exam table
    private User user;

    public void assignExam(Exam examToAssign) {
        this.exam = examToAssign;
        Result result = examToAssign.getResults().stream().filter(x -> x.getResultid() == this.resultid).findFirst().orElse(null);
        examToAssign.getResults().add(result);
    }
}
