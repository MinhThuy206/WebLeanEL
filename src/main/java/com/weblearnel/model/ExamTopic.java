package com.weblearnel.model;

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
@Table(name="exam_topic")
public class ExamTopic {
    @Id
    @Column(name = "et_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int examTopicId;

    @Column(name = "ex_id")
    int examId;

    @Column(name = "tp_id")
    int topicId;

    @Column(name = "et_topic_percent")
    int examTopicPercent;
}
