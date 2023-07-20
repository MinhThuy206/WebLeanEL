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
@Table(name = "exam_topic")
public class ExamTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "et_id")
    private long examTopicId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_ex_id", referencedColumnName = "ex_id")
    private Exam exam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tp_id", referencedColumnName = "tp_id")
    private Topic topic;


    @Column(name = "topic_percent")
    private double topicPercent;

    public void assignExam(Exam examToAssign) {
        this.exam = examToAssign;
        ExamTopic examTopic = examToAssign.getExamTopics().stream().filter(x -> x.getExamTopicId() == this.examTopicId).findFirst().orElse(null);
        examToAssign.getExamTopics().add(examTopic);
    }

}
