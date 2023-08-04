package com.weblearnel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TestController {
    // @Autowired
    // private TopicPassedService topicPassedService;

    @GetMapping("/exam/{user_id}")
    public String test(@PathVariable Long user_id, Model model){
        // lấy topic đã pass của user gán vào model
        // List<TopicPassed> topicsOfTest = topicPassedService.findTopicPassedFromUser(user_id);
        // for (int i = 0; i < topicsOfTest.size(); i++) {
        //     String topicName = topicsOfTest.get(i).getTopic().getName();
        //     model.addAttribute("topicsOfTest" + String.valueOf(i), topicName);

        //     System.out.println(topicsOfTest.get(i).getTopic().getName());
        // }
        return "test-eng1";
    }

    @GetMapping("/exam")
    public String levelTest(){
        return "exam/test";
    }

}
