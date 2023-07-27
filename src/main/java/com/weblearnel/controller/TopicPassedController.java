package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.TopicPassed;
import com.weblearnel.service.TopicPassedService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TopicPassedController {
    @Autowired
    private TopicPassedService topicPassedService;
    
    @GetMapping("/topicPassed")
    public List<TopicPassed> getTopicPassed() {
        return topicPassedService.getTopicPassed();
    }

    @PostMapping("/topicPassed")
    public TopicPassed addTopicPassed(@RequestBody TopicPassed topicPassed) {
        return topicPassedService.addTopicPassed(topicPassed);
    }

    @PutMapping("/topicPassed/{topic_id}/users/{user_id}")
    public TopicPassed addTopicPassedToUser(@PathVariable("topic_id") Long topicId, @PathVariable("user_id") Long userId) {
        return topicPassedService.addTopicPassedToUser(topicId, userId);
    }
}
