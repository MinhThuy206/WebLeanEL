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
import com.weblearnel.model.Result;
import com.weblearnel.service.ResultService;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/results")
    public List<Result> findResults() {
        return resultService.findResults();
    }

    @PostMapping("/results")
    public void addResult(@RequestBody Result result) {
        resultService.addResult(result);
    }

    // @GetMapping("/results/{id}")
    // @GetMapping("/results/{rs_id}")
    // public String findUserFromResult(@PathVariable("rs_id") Long id) {
    //     return resultService.findUserFromResult(id);
    // }

    @PutMapping("/results/{rs_id}/exams/{exam_id}")
    public Result assignExamToResult(@PathVariable("rs_id") long rs_id, @PathVariable("exam_id") long exam_id) {
        return resultService.assignExamToResult(rs_id, exam_id);
    }
    
    @GetMapping("/results/{rs_id}/exams")
    public Exam findExamFromResult(@PathVariable("rs_id") long rs_id) {
        return resultService.findExamFromResult(rs_id);
    }
}
