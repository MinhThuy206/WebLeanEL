package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Topic;
import com.weblearnel.model.User;
import com.weblearnel.service.TopicService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getTopics();
    }

    @PostMapping("/topics")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    // gán user cho topic thể hiện đã pass topic đó
    @PutMapping("/topic/{topic_id}/user/{user_id}")
    public Topic assignUserToTopic(@PathVariable("topic_id") Long topic_id,
            @PathVariable("user_id") Long user_id) {
                
        return topicService.assignUserToTopic(topic_id, user_id);
    }
    // lấy các topic đã pass của user từ user id
    @GetMapping("/topic/users/{user_id}")
    public List<Topic> findTopicFromUser(@PathVariable("user_id") Long user_id) {
        return topicService.findTopicFromUser(user_id);
    }

    // lấy các user đã pass topic từ topic id
    @GetMapping("/topic/{topic_id}/users")
    public List<User> findUsersFromTopic(@PathVariable("topic_id") Long topic_id) {
        return topicService.findUsersFromTopic(topic_id);
    }
}
