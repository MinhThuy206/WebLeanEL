package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.model.Topic;
import com.weblearnel.service.QuestionService;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> findQuestions() {
        return questionService.findQuestions();
    }

    @PostMapping("/questions")
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @GetMapping("/questions/{id}/level")
    public Level findLevelFromQuestion(@PathVariable("level_id") Long id) {
        return questionService.findLevelFromQuestion(id);
    }

    @PostMapping("/questions/{q_id}/level/{level_id}")
    public Question assignLevelToQuestion(@PathVariable("q_id") Long q_id, @PathVariable("level_id") Long level_id) {
        return questionService.assignLevelToQuestion(q_id, level_id);
    }

    @GetMapping("/questions/{q_id}/topic")
    public Topic findTopicFromQuestion(@PathVariable("q_id") Long q_id) {
        return questionService.findTopicFromQuestion(q_id);
    }

    @PostMapping("/questions/{q_id}/topic/{topic_id}")
    public Question assignTopicToQuestion(@PathVariable("q_id") Long q_id,
            @PathVariable("topic_id") Long topic_id) {
        return questionService.assignLevelToQuestion(q_id, topic_id);
    }
}
