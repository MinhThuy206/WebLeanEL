package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Exam;
import com.weblearnel.model.ExamTopic;
import com.weblearnel.service.ExamTopicService;

@RestController
public class ExamTopicController {
    @Autowired
    private ExamTopicService examTopicService;

    @GetMapping("/examtopics")
    public List<ExamTopic> findExamTopics() {
        return examTopicService.findExamTopics();
    }

    @PostMapping("/examtopics")
    public void addExamTopic(@RequestBody ExamTopic examTopic) {
        examTopicService.addExamTopic(examTopic);
    }

    @PutMapping("/examtopics/{et_id}/exams/{exam_id}")
    public ExamTopic assignExamToExamTopic(@PathVariable("et_id") long et_id, @PathVariable("exam_id") long exam_id) {
        return examTopicService.assignExamToExamTopic(et_id, exam_id);
    }

    @GetMapping("/examtopics/{et_id}/exams")
    public Exam findExamFromExamTopic(@PathVariable("et_id") long et_id) {
        return examTopicService.findExamFromResult(et_id);
    }
}
