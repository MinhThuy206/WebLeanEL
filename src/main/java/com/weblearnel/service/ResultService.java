package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Exam;
import com.weblearnel.model.Result;
import com.weblearnel.repository.ExamRepository;
import com.weblearnel.repository.ResultRepository;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ExamRepository examRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> findResults() {
        return resultRepository.findAll();
    }

    public Result assignExamToResult(long rs_id, long exam_id) {
        Result result = resultRepository.findById(rs_id).get();
        Exam exam = examRepository.findById(exam_id).get();
        result.assignExam(exam);
        return resultRepository.save(result);
    }

    public Exam findExamFromResult(long rs_id) {
        Result result = resultRepository.findById(rs_id).get();
        Exam exam = result.getExam();
        return exam;
    }

    public void addResult(Result result) {
        resultRepository.save(result);
    }

    

    
    
}
