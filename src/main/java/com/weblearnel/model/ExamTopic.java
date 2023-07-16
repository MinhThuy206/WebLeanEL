package com.weblearnel.model;

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
    private long Id;

    @ManyToOne
    @JoinColumn(name = "ex_id")
    private long examId;

    @ManyToOne
    @JoinColumn(name = "tp_id")
    private long topicId;

    @Column(name = "topic_percent")
    private Integer TopicPercent;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Topic topic;

}
