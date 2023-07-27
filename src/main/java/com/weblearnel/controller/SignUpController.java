package com.weblearnel.controller;

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
import com.weblearnel.model.User;
import com.weblearnel.model.Word;
import com.weblearnel.registration.RegistrationService;
import com.weblearnel.service.QuestionService;
import com.weblearnel.service.TopicService;
import com.weblearnel.service.WordService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class SignUpController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private WordService wordService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;

    // Render form tạo user
    @GetMapping("/users/showForm")
    public String showForm() {
        return "test";
    }

    // Xử lý form tạo user và redirect về trang tạo user
    @PostMapping("/submitForm")
    public String submitForm(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int level = Integer.parseInt(request.getParameter("level"));
        User user = new User(username, password, fullname,address,phone, email, level);
        registrationService.register(user);
        
        
        return "redirect:/users/showForm";
    }

    // Render form tạo word
    @GetMapping("/admin/{topic_name}/createWord")
    public String wordForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);
        return "wordForm";
    }

    // Xử lý form tạo word và redirect về trang tạo word
    @PostMapping("/admin/{topic_name}/submitWord")
    public String submitWordForm(HttpServletRequest request, @PathVariable("topic_name") String topic_name) {
        String name = request.getParameter("name");
        String mean = request.getParameter("mean");
        String attributes = request.getParameter("attributes");
        String example = request.getParameter("example");
        String imageUrl = "static/images/" + name + ".jpg";
        String pronounce = "static/audio/" + name + ".mp3";
        Word word = new Word(name, mean, attributes, example, imageUrl, pronounce);
        Topic topic = topicService.getTopicByName(topic_name);
        word.assignTopic(topic);
        wordService.addWord(word);
        return "redirect:/admin/" + topic_name + "/createWord";
    }

    // Render form tạo topic
    @GetMapping("/admin/createTopic")
    public String topicForm() {
        return "topicForm";
    }

    // Xử lý form tạo topic và redirect về trang tạo word cho topic đó
    @PostMapping("/admin/submitTopic")
    public String submitTopicForm(HttpServletRequest request) {
        String topic_name = request.getParameter("name");
        String description = request.getParameter("description");
        Topic topic = new Topic(topic_name, description);
        if(topicService.getTopicByName(topic_name) == null) {
            topicService.addTopic(topic);
        } 
        return "redirect:/admin/" + topic_name + "/createWord";
    }

    @GetMapping("/admin/{topic_name}/createQuestion")
    public String questionForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);
        return "QuestionForm";
    }

    @PostMapping("/admin/{topic_name}/submitQuestion")
    public String submitQuestionForm(HttpServletRequest request, @PathVariable("topic_name") String topic_name) {
        String content = request.getParameter("content");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        String answer = request.getParameter("answer");
        String explain = request.getParameter("explain");
        int type = Integer.parseInt(request.getParameter("type"));

        Question question = new Question(content, option1, option2, option3, option4, answer, explain, type);
        Topic topic = topicService.getTopicByName(topic_name);
        question.assignTopic(topic);
        if (content == wordService.getWordByName(content).getName()) {
            Word word = wordService.getWordByName(content);
            Level level = word.getLevel();
            question.assignLevel(level);
        }
        questionService.addQuestion(question);
        return "redirect:/admin/" + topic_name + "/createQuestion";
    }
}
