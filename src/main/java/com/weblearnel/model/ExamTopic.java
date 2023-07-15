package com.weblearnel.model;

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
@Table(name = "exam_topic")
public class ExamTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "ex_id")
    private long examId;

    @Column(name = "tp_id")
    private long topicId;

    @Column(name = "topic_percent")
    private Integer TopicPercent;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Topic topic;

}
