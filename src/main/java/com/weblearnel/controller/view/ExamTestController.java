package com.weblearnel.controller.view;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.weblearnel.model.Answer;
import com.weblearnel.model.Exam;
import com.weblearnel.model.Question;
import com.weblearnel.model.Result;
import com.weblearnel.model.User;
import com.weblearnel.repository.ResultRepository;
import com.weblearnel.service.AnswerService;
import com.weblearnel.service.ExamService;
import com.weblearnel.service.QuestionService;
import com.weblearnel.service.ResultService;
import com.weblearnel.service.UserService;

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

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/exam/overview/{user_id}/{exam_id}")
    public String showExamOverView(@PathVariable("user_id") Long user_id, Model model,
            @PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/overview";
    }

    @GetMapping("/exam/test/{user_id}/{exam_id}")
    public String showExamTest(@PathVariable("user_id") Long user_id, Model model,
            @PathVariable("exam_id") Long exam_id) {
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
    public String Eng1Test(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/test-eng1";
    }

    @GetMapping("/exam/test_eng2/{user_id}/{exam_id}")
    public String Eng2Test(@PathVariable("user_id") Long user_id, @PathVariable("exam_id") Long exam_id, Model model) {
        List<Question> question1 = examService.getQuestions(exam_id);
        model.addAttribute("questionList", question1);
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        for (Question q : question1) {
            System.out.println(q.getAnswer());
        }
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        return "exam/test-eng2";
    }

    @GetMapping("/exam/test_eng3/{user_id}/{exam_id}")
    public String Eng3Test(@PathVariable("user_id") Long user_id, Model model, @PathVariable("exam_id") Long exam_id) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        Exam exam = examService.getExamById(exam_id);
        model.addAttribute("exam", exam);
        // Set<Result> results = exam.getResults();
        // List<LocalDateTime> dates = new ArrayList<>();
        // for (Result result : results) {
        //     dates.add(result.getResultDatetime());
        // }
        // List<Result> results2 = new ArrayList<>(results);
        // results2.sort((Result r1, Result r2) -> r1.getResultDatetime().compareTo(r2.getResultDatetime()));
        Result latest = resultRepository.findLatestResultOfSameExamAndUser(exam_id, user_id);
        model.addAttribute("latest", latest);
        // LocalDateTime latesTime = Collection.max(dates);
        // Comparator<Object> datetimeComparator = Comparator
        //         .comparing(result -> result.getDatetime().minusSeconds(currentTime.toEpochSecond()));
        return "exam/test-eng3";
    }

    @PostMapping("/exam/submit/{email}/{examId}")
    public ResponseEntity<String> submitExam(@RequestBody List<Map<String, Object>> userAnswers,
            @PathVariable("email") String email,
            Model model, @PathVariable("examId") Long examId) {
        User user = userService.getUserByEmail(email);
        System.out.println(userAnswers);
        try {
            for (Map<String, Object> userAnswer : userAnswers) {
                Answer answer = new Answer();
                examId = Long.valueOf(userAnswer.get("examId").toString());

                if (userAnswer.containsKey("questionId")) {
                    Long questionId = Long.valueOf(userAnswer.get("questionId").toString());
                    Question question = questionService.getQuestionById(questionId);
                    answer.assignQuestion(question);
                    answer.setUserAnswer(userAnswer.get("userAnswer").toString());
                    answer.assignUser(user);
                    answerService.addAnswer(answer);
                }
            }
            Exam exam = examService.getExamById(examId);
            List<Answer> userAnswersCheck = answerService.getAnswersByUserId(user.getId());
            double score = answerService.checkAnswers(userAnswersCheck, user.getId());
            Result result = new Result(score);
            result.assignUser(user);
            result.assignExam(exam);
            result.setResultType(1);// 1 la exam 0 la hoc word
            resultService.addResult(result);

            model.addAttribute("score", score);
            return ResponseEntity.ok("redirect:/exam/test_eng3" + "/" + user.getId() + "/" + examId);
            // return "redirect:/exam/test-eng3" + "/" + user.getId() + "/" + examId;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: ");
        }

    }

}
