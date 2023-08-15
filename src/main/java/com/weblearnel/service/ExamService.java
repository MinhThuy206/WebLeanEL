package com.weblearnel.service;

import java.util.List;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Exam;
import com.weblearnel.repository.ExamRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private LevelService levelService;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public void addExam(Exam exam) {
        examRepository.save(exam);
    }


    public List<Question> getQuestions(long exam_id){
        Exam exam = examRepository.findById(exam_id).get();
        Level level = exam.getLevel();
        List<Question> questions = levelService.findQuestionFromLevel(level.getId());
        return questions;
    }
}
