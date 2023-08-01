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
import com.weblearnel.model.TopicPassed;
import com.weblearnel.model.User;
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

    // tìm topic của topicPassed
    @GetMapping("/topicPassed/{topicPassed_id}/topic")
    public Topic findTopicFromTopicPassed(@PathVariable("topicPassed_id") Long topicPassed_id) {
        return topicPassedService.findTopicFromTopicPassed(topicPassed_id);
    }

    // gán topic cho topicPassed thể hiện topic đã pass là gì
    @PutMapping("/topicPassed/{topicPassed_id}/topic/{topic_id}")
    public TopicPassed assignTopicToTopicPassed(@PathVariable("topicPassed_id") Long topicPassed_id,
            @PathVariable("topic_id") Long topic_id) {
        return topicPassedService.assignTopicToTopicPassed(topicPassed_id, topic_id);
    }

    // gán user cho topicPassed thể hiện đã pass topic đó
    @PutMapping("/topicPassed/{topicPassed_id}/user/{user_id}")
    public TopicPassed assignUserToTopicPassed(@PathVariable("topicPassed_id") Long topicPassed_id,
            @PathVariable("user_id") Long user_id) {
                
        return topicPassedService.assignUserToTopicPassed(topicPassed_id, user_id);
    }

    // lấy các topic đã pass của user từ user id
    @GetMapping("/topicPassed/users/{user_id}")
    public List<TopicPassed> findTopicPassedFromUser(@PathVariable("user_id") Long user_id) {
        return topicPassedService.findTopicPassedFromUser( user_id);
    }

    // lấy các user đã pass topic từ topic id
    @GetMapping("/topicPassed/{topicPassed_id}/users")
    public List<User> findUsersFromTopicPassed(@PathVariable("topicPassed_id") Long topicPassed_id) {
        return topicPassedService.findUsersFromTopicPassed(topicPassed_id);
    }

}
