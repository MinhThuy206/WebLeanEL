package com.weblearnel.controller.rest;

import com.weblearnel.model.Answer;
import com.weblearnel.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @PostMapping("/submit-answers")
    public void saveUserAnswer(@RequestBody Answer userAnswer) {
        answerService.addAnswer(userAnswer);
        System.out.println("save");
    }
}
