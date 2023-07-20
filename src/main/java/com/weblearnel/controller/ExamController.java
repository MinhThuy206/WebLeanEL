package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Exam;
import com.weblearnel.service.ExamService;

@RestController
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/exams")
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @PostMapping("/exams")
    public void addExam(@RequestBody Exam exam) {
        examService.addExam(exam);
    }
    

}
