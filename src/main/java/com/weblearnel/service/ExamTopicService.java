package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Exam;
import com.weblearnel.model.ExamTopic;
import com.weblearnel.repository.ExamRepository;
import com.weblearnel.repository.ExamTopicRepository;

@Service
public class ExamTopicService {
    @Autowired
    private ExamTopicRepository examTopicRepository;

    @Autowired
    private ExamRepository examRepository;

    public ExamTopicService(ExamTopicRepository examTopicRepository) {
        this.examTopicRepository = examTopicRepository;
    }

    public List<ExamTopic> findExamTopics() {
        return examTopicRepository.findAll();
    }

    public void addExamTopic(ExamTopic examTopic) {
        examTopicRepository.save(examTopic);
    }

    public ExamTopic assignExamToExamTopic(long et_id, long exam_id) {
        ExamTopic examTopic = examTopicRepository.findById(et_id).get();
        Exam exam = examRepository.findById(exam_id).get();
        examTopic.assignExam(exam);
        return examTopicRepository.save(examTopic);

    }

    public Exam findExamFromResult(long et_id) {
        ExamTopic examTopic = examTopicRepository.findById(et_id).get();
        Exam exam = examTopic.getExam();
        return exam;
    }
}
