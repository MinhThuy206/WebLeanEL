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

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String ShowsignIn() {
        return "authentication/sign-in";
    }

    @GetMapping("/index/{user_id}")
    public String index(@PathVariable("user_id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        System.out.println("index");
        return "index";
    }

    @PostMapping("/checklogin")
    public ResponseEntity<String> checkLogin(@RequestBody User user, Model model) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            User userCheck = userService.getUser(username);
            if (userCheck.getPassword().equals(password)) {
                System.out.println("login thanh cong");
                model.addAttribute("user", userCheck);
                // String redirectUrl = "/index/" + user.getId();
                return ResponseEntity.ok("redirect:" + "/index/" + userCheck.getId());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        } catch (Exception e) {
            System.out.println("login failed ");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

}
