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
@Table(name="result")
public class Result {
    @Id
    @Column(name = "rs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int resultId;

    @Column(name = "us_id")
    int userId;

    @Column(name = "ex_id")
    int examId;

    @Column(name = "wd_id")
    int wordId;

    @Column(name = "rs_score")
    int resultScore;

    @Column(name = "rs_datetime", columnDefinition = "DATETIME")
    LocalDateTime  resultDatetime;

    @Column(name = "rs_flag")
    int resultFlag;

    @Column(name = "rs_type")
    int resultType;
    

}
