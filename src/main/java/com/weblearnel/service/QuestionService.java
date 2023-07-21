package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.model.Topic;
import com.weblearnel.repository.LevelRepository;
import com.weblearnel.repository.QuestionRepository;
import com.weblearnel.repository.TopicRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Level findLevelFromQuestion(long q_id) {
        Question question = questionRepository.findById(q_id).get();
        Level level = question.getLevel();
        return level;
    }

    public Topic findTopicFromQuestion(long q_id) {
        Question question = questionRepository.findById(q_id).get();
        Topic topic = question.getTopic();
        return topic;
    }

    public Question assignLevelToQuestion(long q_id, long level_id) {
        Question question = questionRepository.findById(q_id).get();
        Level level = levelRepository.findById(level_id).get();
        question.assignLevel(level);
        return questionRepository.save(question);
    }

    public Question assignTopicToQuestion(long q_id, long topic_id) {
        Question question = questionRepository.findById(q_id).get();
        Topic topic = topicRepository.findById(topic_id).get();
        question.assignTopic(topic);
        return questionRepository.save(question);
    }
}
