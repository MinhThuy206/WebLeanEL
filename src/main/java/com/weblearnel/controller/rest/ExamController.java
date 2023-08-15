package com.weblearnel.controller.rest;

import java.util.List;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/exam/{exam_id}")
    public String findQuestion(@PathVariable("exam_id") Long exam_id, Model model) {
        List<Question> questions = examService.getQuestions(exam_id);
        model.addAttribute("questionList", questions);
        return "exam/test-eng2";
    }

}
