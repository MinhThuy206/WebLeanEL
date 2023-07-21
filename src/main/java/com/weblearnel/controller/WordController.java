package com.weblearnel.controller;

import com.weblearnel.model.Word;
import com.weblearnel.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;

    // Add Word
    @PostMapping("/")
    public Word addWord(@RequestBody Word word) {
        return wordService.addWord(word);
    }

    //Get All Words
    @GetMapping("/")
    public List<Word> findAllWords() {
        return wordService.getAllWords();
    }

    // Get One Word
    @GetMapping("/{id}")
    public Word findWord(@PathVariable long id) {
        return wordService.getOneWord(id);
    }

    // update word
    @PutMapping("/{id}")
    public Word updateWord(@PathVariable("id") long id, @RequestBody Word word) {
        return wordService.updateWord(word, id);
    }

    // Delete word
    @DeleteMapping("/{id}")
    public boolean deleteWord(@PathVariable int id) {
        return wordService.deleteWord(id);
    }
}
