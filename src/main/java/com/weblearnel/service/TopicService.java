package com.weblearnel.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Topic;
import com.weblearnel.model.User;
import com.weblearnel.model.Word;
import com.weblearnel.repository.TopicRepository;
import com.weblearnel.repository.UserRepository;
import com.weblearnel.repository.WordRepository;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic getTopicByName(String topicName) {
        return topicRepository.findByName(topicName);
    }

    public Topic getTopicById(Long topic_id) {
        return topicRepository.findById(topic_id).orElse(null);
    }

    public Topic assignUserToTopic(Long topic_id, Long user_id) {
        if(topicRepository.checkRecordExist(topic_id, user_id) == 0) {
            Set<User> userSet = null;
            Topic topic = topicRepository.findById(topic_id).orElse(null);
            User user = userRepository.findById(user_id).orElse(null);
            userSet = topic.getUsers();
            userSet.add(user);
            topic.setUsers(userSet);
            return topicRepository.save(topic);
        } else {
            return null;
        }
    }

    public List<Topic> findTopicFromUser(Long user_id) {
        return topicRepository.findByUsers(user_id);
    }

    public List<User> findUsersFromTopic(Long topic_id) {
        return userRepository.findUsersFromTopic(topic_id);
    }

    public List<Word> findWordsFromTopic(Long topic_id) {
        Topic topic = topicRepository.findById(topic_id).orElse(null);
        if(topic != null) {
            return wordRepository.findByTopic(topic_id);
        }
        return null;
    }

    public List<Topic> findTopicFromLevel(Long level_id) {
        return topicRepository.findByLevel(level_id);
    }
}
