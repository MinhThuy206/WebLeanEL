package com.weblearnel.service;

import com.weblearnel.model.Answer;
import com.weblearnel.model.Question;
import com.weblearnel.repository.AnswerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                Question question = entityManager.find(Question.class, userAnswer.getQuestion());
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

}
