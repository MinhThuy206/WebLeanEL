package com.weblearnel.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.TopicPassed;
import com.weblearnel.model.User;
import com.weblearnel.repository.TopicPassedRepository;
import com.weblearnel.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TopicPassedService {
    @Autowired
    private TopicPassedRepository topicPassedRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TopicPassed> getTopicPassed() {
        return topicPassedRepository.findAll();
    }

    public TopicPassed addTopicPassed(TopicPassed topicPassed) {
        return topicPassedRepository.save(topicPassed);
    }

    public TopicPassed addTopicPassedToUser(Long topicId, Long userId) {
        Set<User> users = new HashSet<>();
        TopicPassed topicPassed = topicPassedRepository.findById(topicId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        users = topicPassed.getUsers();
        users.add(user);
        topicPassed.setUsers(users);
        return topicPassedRepository.save(topicPassed);
        // return null;
    }
}
