// package com.weblearnel.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.weblearnel.model.Level;
// import com.weblearnel.model.Question;
// import com.weblearnel.model.Topic;
// import com.weblearnel.repository.QuestionRepository;

// @Service
// public class QuestionService {
// @Autowired

// @Autowired
// private QuestionRepository questionRepository;

// public List<Question> getAllQuestions() {
// return questionService.getAllQuestions();
// }

// public void addQuestion(Question question) {
// questionService.addQuestion(question);
// }

// public QuestionService(QuestionRepository questionRepository) {
// this.questionRepository = questionRepository;
// }

// public Level findLevelFromQuestion(long q_id) {
// Question question = questionService.findById(q_id).get();
// Level level = question.getLevel();
// return level;
// }

// public Topic findTopicFromQuestion(long q_id) {
// Question question = questionService.findById(q_id).get();
// Topic topic = question.getTopic();
// return topic;
// }

// public Question assignLevelToQuestion(long q_id, long level_id) {
// Question question = questionService.findById(q_id).get();
// Level level = levelService.findById(level_id).get();
// question.assignLevel(level);
// return questionService.save(question);
// }

// public Question assignTopicToQuestion(long q_id, long topic_id) {
// Question question = questionService.findById(q_id).get();
// Topic topic = topicService.findById(topic_id).get();
// question.assignTopic(topic);
// return questionService.save(question);
// }
// }
