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

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="UserAnswer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userAnswer_id")
    private long userAnswerId;

    @Column(name = "UserAnswer")
    private String userAnswer ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ques_id", referencedColumnName = "question_id") // id = id in the exam table
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user_id", referencedColumnName = "user_id") // id = id in the exam table
    private User user;

    public void assignQuestion(Question question) {
        this.question = question;
    }

    public void assignUser(User user) {
        this.user = user;
    }
}
