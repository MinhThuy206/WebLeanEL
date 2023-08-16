package com.weblearnel.controller.view;

import java.util.List;

import com.weblearnel.model.*;
import com.weblearnel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ExamTestController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @GetMapping("/exam/overview/{user_id}/{exam_id}")
    public String showExamOverView(@PathVariable("user_id") Long user_id, Model model,@PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/overview";
    }

    @GetMapping("/exam/test/{user_id}/{exam_id}")
    public String showExamTest(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id") Long exam_id) {
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/test";
    }

    @GetMapping("/exam/A1/{user_id}/{exam_id}")
    public String A1Test(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/A1";
    }

    @GetMapping("/exam/A2/{user_id}")
    public String A2Test(@PathVariable("user_id") Long user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/A2";
    }

    @GetMapping("/exam/B1/{user_id}")
    public String B1Test(@PathVariable("user_id") Long user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/B1";
    }

    @GetMapping("/exam/B2/{user_id}")
    public String B2Test(@PathVariable("user_id") Long user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/B2";
    }

    @GetMapping("/exam/C1/{user_id}")
    public String C1Test(@PathVariable Long user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/C1";
    }

    @GetMapping("/exam/C2/{user_id}")
    public String C2Test(@PathVariable("user_id") Long user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "exam/C2";
    }

    @GetMapping("/exam/test_eng1/{user_id}/{exam_id}")
    public String Eng1Test(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id" ) Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/test-eng1";
    }

    @GetMapping("/exam/test_eng2/{user_id}/{exam_id}")
    public String Eng2Test(@PathVariable("user_id") Long user_id,@PathVariable("exam_id" ) Long exam_id
                           ,Model model) {
        List<Question> question1 = examService.getQuestions(exam_id);
        model.addAttribute("questionList", question1);
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        for (Question q: question1
             ) {
            System.out.println(q.getAnswer());
        }
        return "exam/test-eng2";
    }

    @GetMapping("/exam/test_eng3/{user_id}/{exam_id}")
    public String Eng3Test(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/test-eng3";
    }

    @PostMapping("/exam/submit/{user_id}")
    public String submitExam(@RequestBody List<Answer> userAnswers, @PathVariable("user_id") Long user_id,
            Model model) {
        double score = answerService.checkAnswers(userAnswers, user_id);
        Result result = new Result(score);
        resultService.addResult(result);

        model.addAttribute("score", score);
        return "redirect:/exam/test-eng3";
    }

    @GetMapping("/exam/result")
    public String showResult(@RequestParam int score, Model model) {
        model.addAttribute("score", score);
        return "/exam/test-eng3";
    }
//    @PostMapping("/exam/{question_id}")
//    public String showQuestion(@PathVariable long question_id, Model model){
//        Question question = questionService.getQuestionById(question_id);
//        model.addAttribute("questionlist",  question);
//        return "redirect:/exam/test-eng2";
//    }

}
