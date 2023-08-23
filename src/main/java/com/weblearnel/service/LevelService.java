package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.repository.LevelRepository;
import com.weblearnel.repository.QuestionRepository;

@Service
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public Level findLevelById(String id) {
        return levelRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    public void addLevel(Level level) {
        levelRepository.save(level);
    }

    public Level getLevelById(Long level) {
        return levelRepository.findById(level).get();
    }

    public List<Question> findQuestionFromLevel(Long level_id){
        Level level = levelRepository.findById(level_id).orElse(null);
        if(level!= null){
            return questionRepository.findByLevel(level_id);
        }
        return null;
    }
}
