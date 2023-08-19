package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Answer;
import com.weblearnel.model.Question;
import com.weblearnel.repository.AnswerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @PersistenceContext
    private EntityManager entityManager;
    public double checkAnswers (List<Answer> userAnswers, long user_id) {

        int correctCount = 0;

        for (Answer userAnswer : userAnswers) {
            if(userAnswer.getUser().getId() == user_id) {
                Question question = entityManager.find(Question.class, userAnswer.getQuestion().getQuestion_id());
                if (question != null && question.getAnswer().equals(userAnswer.getUserAnswer())) {
                    correctCount++;
                }
            }
        }

        int totalQuestions = userAnswers.size();
        double score = (double) correctCount / totalQuestions * 100;

        return score;
    }

    public void addAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public List<Answer> getAnswersByUserId(long user_id) {
        return answerRepository.findByUserId(user_id);
    }

}
