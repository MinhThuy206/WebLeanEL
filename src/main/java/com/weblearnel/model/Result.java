package com.weblearnel.model;

import java.time.LocalDateTime;

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
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "score")
    private int Score;

    @Column(name = "datetime", columnDefinition = "DATETIME")
    private LocalDateTime resultDatetime;

    @Column(name = "rs_flag")
    private int resultFlag;

    @Column(name = "rs_type")
    private int resultType;

    @ManyToOne
    @JoinColumn(name="word_id", referencedColumnName = "id")
    private Word word;

    @ManyToOne
    @JoinColumn(name="exam_id", referencedColumnName = "id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

}
