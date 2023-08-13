package com.weblearnel.controller.view;

import com.weblearnel.model.User;
import com.weblearnel.service.ExamService;
import com.weblearnel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ExamTestController {
    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @GetMapping("/exam/overview/{user_id}")
    public String showExamOverView(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/overview";
    }

    @GetMapping("/exam/test/{user_id}")
    public String showExamTest(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/test";
    }

    @GetMapping("/exam/A1/{user_id}")
    public String A1Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/A1";
    }
    @GetMapping("/exam/A2/{user_id}")
    public String A2Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/A2";
    }

    @GetMapping("/exam/B1/{user_id}")
    public String B1Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/B1";
    }

    @GetMapping("/exam/C1/{user_id}")
    public String C1Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/C1";
    }

    @GetMapping("/exam/C2/{user_id}")
    public String C2Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/C2";
    }


    @GetMapping("/exam/test_eng1/{user_id}")
    public String Eng1Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/test-eng1";
    }

    @GetMapping("/exam/test_eng2/{user_id}")
    public String Eng2Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/test-eng2";
    }

    @GetMapping("/exam/test_eng3/{user_id}")
    public String Eng3Test(@PathVariable Long user_id, Model model){
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/exam/test-eng3";
    }
}
