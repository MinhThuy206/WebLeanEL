package com.weblearnel.controller;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String ShowsignIn() {
        return "login";
    }

    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.getUser(username);
        if(!user.getPassword().equals(password)){
            System.out.println("login thanh cong");
            return "/index";
        }
        System.out.println("login that bai");
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}
