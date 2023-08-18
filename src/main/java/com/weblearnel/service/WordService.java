package com.weblearnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Topic;
import com.weblearnel.model.Word;
import com.weblearnel.repository.TopicRepository;
import com.weblearnel.repository.WordRepository;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private TopicRepository topicRepository;
    //add word
    public Word addWord(Word word) {
        if (word != null) {
            return wordRepository.save(word);
        }
        return null;
    }

    // find all words

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    //find word

    public Word getOneWord(long id) {
        return wordRepository.findById(id).get();
    }

    // update word
    public Word updateWord(Word word, long id) {
        if (word != null) {
            Word word1 = wordRepository.getReferenceById(id);
            word1.setName(word.getName());
            word1.setMean(word.getMean());
            word1.setAttribute(word.getAttribute());
            word1.setExample(word.getExample());
            word1.setImageUrl(word.getImageUrl());
            word1.setPronounce(word.getPronounce());
            return wordRepository.save(word1);
        }
        return null;
    }

    // Delete word
    public boolean deleteWord(long id) {
        if (id >= 1) {
            Word word = wordRepository.getReferenceById(id);
            wordRepository.delete(word);
            return true;
        }
        return false;
    }

    public Word assignExamToResult(long word_id, long topic_id) {
        Word word = wordRepository.findById(word_id).get();
        Topic topic = topicRepository.findById(topic_id).get();
        word.assignTopic(topic);
        return wordRepository.save(word);
    }

    public Word getWordByName(String content) {
        return wordRepository.findByName(content);
    }

    public List<Word> getWordsByTopicId(Long topic_id) {
        return wordRepository.findByTopic(topic_id);
    }
}

