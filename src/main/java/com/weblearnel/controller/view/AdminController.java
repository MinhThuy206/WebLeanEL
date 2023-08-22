package com.weblearnel.controller.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.weblearnel.service.LevelService;
import com.weblearnel.service.QuestionService;
import com.weblearnel.service.TopicService;
import com.weblearnel.service.UserService;
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

    @Autowired
    private UserService userService;
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
        Level level = topic.getLevel();
        question.assignLevel(level);
        System.out.println(answer);

        questionService.addQuestion(question);
        return "redirect:/admin/" + topic_name + "/createQuestion";
    }

    @GetMapping("admin/users/view/{user_id}")
    public String viewUsers(@PathVariable("user_id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/users/view";
    }

    @GetMapping("admin/users/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        User admin = userService.getUserById(50);
        model.addAttribute("users", users);
        model.addAttribute("admin", admin);
        return "admin/users/list";
    }

    @PostMapping("admin/users/addUser")
    public String handleAdminAddUser(HttpServletRequest request) {
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");
        Integer role;
        if (request.getParameter("user_role") == "Administrator") {
            role = 1;
        } else {
            role = 0;
        }
        String fullname = request.getParameter("full_name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("phone");
        String address = request.getParameter("address");
        Integer level = 1;
        User user = new User(username, password, fullname, address, mobile, email, level);
        user.setRole(role);
        userService.addUser(user);

        return "redirect:/admin/users/list";
    }
    @PostMapping("admin/users/deleteUser")
    public ResponseEntity<String> handleAdminDeleteUser(@RequestBody Map<String, Object> requestbody) {
        String userName = requestbody.get("username").toString();
        User user = userService.getUser(userName);
        user.setEnabled(false);
        userService.addUser(user);
        return ResponseEntity.ok("redirect:/admin/users/list");
    }

    // @GetMapping("admin/users/deleteUser/{user_id}")
    // public String handleAdminDeleteUser(@PathVariable("user_id") long id) {
    //     // String userName = requestbody.get("username").toString();
    //     User user = userService.getUserById(id);
    //     user.setEnabled(false);
    //     userService.addUser(user);
    //     return "redirect:/admin/users/list";
    // }

    @GetMapping("admin/topics/list")
    public String listTopics(Model model) {
        List<Topic> topics = topicService.getTopics();
        model.addAttribute("topics", topics);

        return "admin/topics/list";
    }

    @GetMapping("admin/topics/view/{topic_id}")
    public String viewTopics(@PathVariable("topic_id") long id, Model model) {
        Topic topic = topicService.getTopicById(id);
        List<Word> words = wordService.getWordsByTopicId(id);
        model.addAttribute("words", words);
        model.addAttribute("topic", topic);
        return "admin/topics/view";
    }

    @PostMapping("admin/topics/addTopic")
    public String handleAdminAddTopic(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Long level = Long.parseLong(request.getParameter("level"));
        Level topicLevel = levelService.getLevelById(level);
        Topic topic = new Topic(name, description);
        topic.assignLevel(topicLevel);
        if(topicService.getTopicByName(name) == null) {
            topicService.addTopic(topic);
        }

        return "redirect:/admin/topics/list";
    }

    @PostMapping("admin/topics/updateTopic/{topic_id}")
    public String handleAdminUpdateTopic(HttpServletRequest request, @PathVariable("topic_id") long id) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Long level = Long.parseLong(request.getParameter("level"));
        Level topicLevel = levelService.getLevelById(level);
        Topic topic = topicService.getTopicById(id);
        topic.setName(name);
        topic.setDescription(description);
        topic.assignLevel(topicLevel);
        topicService.addTopic(topic);

        return "redirect:/admin/topics/view/" + id;
    }

    @PostMapping("admin/topics/deleteTopic")
    public ResponseEntity<String> handleAdminDeleteTopic(@RequestBody Map<String, Object> requestbody) {
        String topicName = requestbody.get("topicName").toString();
        Topic topic = topicService.getTopicByName(topicName);
        topic.setOutdated(true);
        topicService.addTopic(topic);
        return ResponseEntity.ok("redirect:/admin/topics/list");
    }

    @PostMapping("admin/topics/addWord/{topic_id}")
    public String handleAdminAddWord(HttpServletRequest request, @PathVariable("topic_id") long id) {
        String name = request.getParameter("name");
        String attribute = request.getParameter("attributes");
        String pronounce = request.getParameter("pronounce");
        String mean = request.getParameter("mean");
        String example = request.getParameter("example");
        Word word = new Word(name, mean, attribute, example, pronounce);
        Topic topic = topicService.getTopicById(id);
        word.assignTopic(topic);
        Level topicLevel = topic.getLevel();
        word.assignLevel(topicLevel);
        wordService.addWord(word);

        return "redirect:/admin/topics/view/" + id;
    }

    @GetMapping("admin/words/view/{word_id}")
    public String viewWords(@PathVariable("word_id") long id, Model model) {
        Word word = wordService.getOneWord(id);
        model.addAttribute("word", word);
        return "admin/words/view";
    }

    @GetMapping("admin/words/gettingStarted")
    public String gettingStarted() {
        return "admin/words/getting-started";
    }

    @GetMapping("admin/words/list")
    public String listWords(Model model) {
        List<Word> words = wordService.getAllWords();
        model.addAttribute("words", words);

        return "admin/words/list";
    }

    @PostMapping("admin/words/addWord")
    public String handleAdminAddWord(HttpServletRequest request) {
        String name = request.getParameter("name");
        String attribute = request.getParameter("attributes");
        String pronounce = request.getParameter("pronounce");
        String mean = request.getParameter("mean");
        String example = request.getParameter("example");
        Word word = new Word(name, mean, attribute, example, pronounce);
        // Topic topic = topicService.getTopicByName(request.getParameter("topicName"));
        // word.assignTopic(topic);
        // Level topicLevel = topic.getLevel();
        // word.assignLevel(topicLevel);
        wordService.addWord(word);

        return "redirect:/admin/words/list";
    }

    @PostMapping("admin/words/updateWord/{word_id}")
    public String handleAdminUpdateWord(HttpServletRequest request, @PathVariable("word_id") long id) {
        String name = request.getParameter("name");
        String attribute = request.getParameter("attributes");
        String pronounce = request.getParameter("pronounce");
        String mean = request.getParameter("mean");
        String example = request.getParameter("example");
        Word word = wordService.getOneWord(id);
        word.setName(name);
        word.setAttribute(attribute);
        word.setPronounce(pronounce);
        word.setMean(mean);
        word.setExample(example);
        wordService.addWord(word);

        return "redirect:/admin/words/view/" + id;
    }
    @GetMapping("admin/questions/list")
    public String listQuestions(Model model) {
        List<Question> questions = questionService.findQuestions();
        model.addAttribute("questions", questions);

        return "admin/questions/list";
    }

    @GetMapping("admin/questions/view/{question_id}")
    public String viewQuestions(@PathVariable("question_id") long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "admin/questions/view";
    }

    @GetMapping("admin/questions/gettingStarted")
    public String gettingStartedQuestion() {
        return "admin/questions/getting-started";
    }

    @PostMapping("admin/questions/addQuestion")
    public String handleAdminAddQuestion(HttpServletRequest request ) {
        String content = request.getParameter("content");
        String answer = request.getParameter("answer");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String topicName = request.getParameter("topicName");
        String explain = request.getParameter("explanation");
        String option4 = "";
        // int type = Integer.parseInt(request.getParameter("type"));

        Question question = new Question(content, option1, option2, option3, option4, answer, explain);
        Topic topic = topicService.getTopicByName(topicName);
        question.assignTopic(topic);
        Level level = topic.getLevel();
        question.assignLevel(level);
        questionService.addQuestion(question);

        return "redirect:/admin/questions/list";
    }

    @PostMapping("admin/questions/updateQuestion/{question_id}")
    public String handleAdminUpdateQuestion(HttpServletRequest request, @PathVariable("question_id") long id) {
        String content = request.getParameter("content");
        String answer = request.getParameter("answer");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String topicName = request.getParameter("topicName");
        String explain = request.getParameter("explanation");
        String option4 = "";
        // int type = Integer.parseInt(request.getParameter("type"));

        Question question = questionService.getQuestionById(id);
        question.setContent(content);
        question.setAnswer(answer);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setExplanation(explain);
        questionService.addQuestion(question);

        return "redirect:/admin/questions/view/" + id;
    }



    
    
}
