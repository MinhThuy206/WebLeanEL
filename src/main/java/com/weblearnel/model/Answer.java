package com.weblearnel.model;

import jakarta.persistence.*;
import lombok.*;

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
}
