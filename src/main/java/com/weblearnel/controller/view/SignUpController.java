package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return "authentication/sign-up";
    }

    // Xử lý form tạo user và redirect về trang tạo user
    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            String fullname = user.getFullname();
            String phone = "0123456789";
            // String phone = request.getParameter("phone");
            // String address = request.getParameter("address");
            // int level = Integer.parseInt(request.getParameter("level"));
            String address = "Hà Nội";
            int level = 1;
            User newUser = new User(username, password, fullname,address,phone, email, level);
            registrationService.register(newUser); // đăng ký user mới vào database
            System.out.println("Đăng ký thành công");
            // String redirectUrl = "{\"redirect\": \"" + "/user/login" + "\"}";
            // return ResponseEntity.ok(redirectUrl);
            return ResponseEntity.ok("redirect:/user/login");
        } catch (Exception e) {
            System.out.println("Đăng ký thất bại");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting the form: " + e.getMessage());
            // return ResponseEntity.ok("redirect:/user/login");
        }
        
    // public String submitForm(HttpServletRequest request) {
        
    }

    // Render form tạo word
    @GetMapping("/admin/{topic_name}/createWord")
    public String wordForm(@PathVariable("topic_name") String topic_name, Model model) {
        model.addAttribute("topicName", topic_name);// thêm topic để biết từ dc tạo thuộc topic nào
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
        word.assignTopic(topic); // gán topic cho word
        wordService.addWord(word); // thêm word vào database
        return "redirect:/admin/" + topic_name + "/createWord"; // redirect về trang tạo word
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