package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Level;
import com.weblearnel.repository.LevelRepository;

@Service
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    public void addLevel(Level level) {
        levelRepository.save(level);
    }
}
