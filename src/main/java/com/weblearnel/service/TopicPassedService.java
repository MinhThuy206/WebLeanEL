package com.weblearnel.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weblearnel.model.Topic;
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
    private TopicService topicService;

    @Autowired
    private UserRepository userRepository;

    public List<TopicPassed> getTopicPassed() {
        return topicPassedRepository.findAll();
    }

    public TopicPassed addTopicPassed(TopicPassed topicPassed) {
        return topicPassedRepository.save(topicPassed);
    }

    
    public TopicPassed assignUserToTopicPassed(Long topicPassed_id, Long user_id) {
        if(topicPassedRepository.checkRecordExist(topicPassed_id, user_id) == 0) {
            Set<User> userSet = null;
            TopicPassed topicPassed = topicPassedRepository.findById(topicPassed_id).orElse(null);
            User user = userRepository.findById(user_id).orElse(null);
            userSet = topicPassed.getUsers();
            userSet.add(user);
            topicPassed.setUsers(userSet);
            return topicPassedRepository.save(topicPassed);
        } else {
            return null;
        }
        
    }

    public Topic findTopicFromTopicPassed(Long q_id) {
        return null;
    }

    public TopicPassed assignTopicToTopicPassed(Long topicPassed_id, Long topic_id) {
        TopicPassed topicPassed = topicPassedRepository.findById(topicPassed_id).orElse(null);
        Topic topic = topicService.getTopicById(topic_id);
        topicPassed.setTopic(topic);
        return topicPassedRepository.save(topicPassed);
    }

    public List<TopicPassed> findTopicPassedFromUser( Long user_id) {
        return topicPassedRepository.findByUsers(user_id);
    }

    public List<User> findUsersFromTopicPassed(Long topicPassed_id) {
        return userRepository.findUsersFromTopicPassed(topicPassed_id);
    }

    
}
