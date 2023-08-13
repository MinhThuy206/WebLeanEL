package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.Level;
import com.weblearnel.model.Question;
import com.weblearnel.model.Topic;
import com.weblearnel.model.Word;
import com.weblearnel.service.LevelService;
import com.weblearnel.service.QuestionService;
import com.weblearnel.service.TopicService;
import com.weblearnel.service.WordService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class AdminController {

    @Autowired
    private WordService wordService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private QuestionService questionService;
    // Render trang chủ admin
    @GetMapping("/admin")
    public String adminPage() {
        return "admin/home-page";
    }

    // Render form tạo topic
    @GetMapping("/admin/createTopic")
    public String topicForm() {
        return "admin/topic-page";
    }

    // Render form chọn tạo question hay word
    @GetMapping("/admin/{topic_name}/create")
    public String createForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);
        return "admin/choice";
    }

    // Render form tạo word
    @GetMapping("/admin/{topic_name}/createWord")
    public String wordForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);// thêm topic để biết từ dc tạo thuộc topic nào
        return "admin/new-word";
    }

    // Xử lý form tạo word và redirect về trang tạo word
    @PostMapping("/admin/{topic_name}/submitWord")
    public String submitWordForm(HttpServletRequest request, @PathVariable("topic_name") String topic_name, Model model) {
        String name = request.getParameter("name");
        String mean = request.getParameter("mean");
        String attributes = request.getParameter("attributes");
        String example = request.getParameter("example");
        String imageUrl = "/assets/images/" + name + ".jpg";
        String pronounce = "/assets/audio/" + name + ".mp3";
        Word word = new Word(name, mean, attributes, example, imageUrl, pronounce);
        Topic topic = topicService.getTopicByName(topic_name);
        word.assignTopic(topic); // gán topic cho word
        wordService.addWord(word); // thêm word vào database

        return "redirect:/admin/" + topic_name + "/createWord"; // redirect về trang tạo word
    }

    // Xử lý form tạo topic và redirect về trang tạo word cho topic đó
    @PostMapping("/admin/submitTopic")
    public String submitTopicForm(HttpServletRequest request, Model model) {
        String topic_name = request.getParameter("name");
        String description = request.getParameter("description");
        Long level = Long.parseLong(request.getParameter("level"));
        Level topicLevel = levelService.getLevelById(level);
        Topic topic = new Topic(topic_name, description);
        topic.assignLevel(topicLevel);
        if(topicService.getTopicByName(topic_name) == null) {
            topicService.addTopic(topic);
        } 
        model.addAttribute("topicName", topic_name);
        return "redirect:/admin/" + topic_name + "/create";
    }

    // Render form tạo question
    @GetMapping("/admin/{topic_name}/createQuestion")
    public String questionForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);
        
        return "admin/multi-choice";
    }

    @PostMapping("/admin/{topic_name}/submitQuestion")
    public String submitQuestionForm(HttpServletRequest request, @PathVariable("topic_name") String topic_name) {
        String content = request.getParameter("content");
        String option1 = request.getParameter("opt1");
        String option2 = request.getParameter("opt2");
        String option3 = request.getParameter("opt3");
        String option4 = request.getParameter("opt4");
        String answer = request.getParameter("answer");
        String explain = request.getParameter("explain");
        int type = Integer.parseInt(request.getParameter("type"));

        Question question = new Question(content, option1, option2, option3, option4, answer, explain, type);
        Topic topic = topicService.getTopicByName(topic_name);
        question.assignTopic(topic);
        System.out.println(answer);
        // Word word = wordService.getWordByName(answer);
        // System.out.println(word.getName());
        // if (answer == wordService.getWordByName(answer).getName()) {
        //     Word word = wordService.getWordByName(answer);
        //     Level level = word.getLevel();
        //     question.assignLevel(level);
        // }
        questionService.addQuestion(question);
        return "redirect:/admin/" + topic_name + "/createQuestion";
    }
    
}
