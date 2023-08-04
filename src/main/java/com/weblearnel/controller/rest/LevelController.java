package com.weblearnel.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Level;
import com.weblearnel.service.LevelService;

@RestController
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping("/levels")
    public List<Level> getLevels() {
        return levelService.getLevels();
    }

    @PostMapping("/levels")
    public void addLevel(@RequestBody Level level) {
        levelService.addLevel(level);
    }
}
