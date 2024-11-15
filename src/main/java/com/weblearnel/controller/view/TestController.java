package com.weblearnel.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.weblearnel.model.Topic;
import com.weblearnel.user.entity.User;
import com.weblearnel.service.TopicService;
import com.weblearnel.service.UserServiceOld;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TestController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserServiceOld userServiceOld;

    // @GetMapping("/exam/{user_id}")
    // public String test(@PathVariable Long user_id, Model model) {
    //     // lấy topic đã pass của user gán vào model
    //     // List<TopicPassed> topicsOfTest =
    //     // topicPassedService.findTopicPassedFromUser(user_id);
    //     // for (int i = 0; i < topicsOfTest.size(); i++) {
    //     // String topicName = topicsOfTest.get(i).getTopic().getName();
    //     // model.addAttribute("topicsOfTest" + String.valueOf(i), topicName);

    //     // System.out.println(topicsOfTest.get(i).getTopic().getName());
    //     // }
    //     return "exam/test-eng1";
    // }

    @GetMapping("/exam")
    public String levelTest() {
        return "exam/test";
    }

    @GetMapping("/learning/a1a2/{user_id}")
    public String levelTestA1(@PathVariable("user_id") Long user_id, Model model) {
        long level = 1;
        User user = userServiceOld.getUserById(user_id);
        model.addAttribute("user", user);
        List<Topic> topics = topicService.findTopicFromLevel(level);
        model.addAttribute("topics", topics);
        return "learning/A1-A2";
    }

    @GetMapping("/learning/b1b2/{user_id}")
    public String levelTestA2(@PathVariable("user_id") Long user_id, Model model) {
        long level = 2;
        User user = userServiceOld.getUserById(user_id);
        model.addAttribute("user", user);
        List<Topic> topics = topicService.findTopicFromLevel(level);
        model.addAttribute("topics", topics);
        return "learning/B1-B2";
    }

    @GetMapping("/exercise")
    public String exercise() {
        return "exam/test";
    }

    @GetMapping("/exam/t")
    public String t() {
        return "exam/t";
    }
}
