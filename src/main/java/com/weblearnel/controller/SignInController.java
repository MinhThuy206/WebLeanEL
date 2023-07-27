package com.weblearnel.controller;

import com.weblearnel.model.User;
import com.weblearnel.registration.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private User userBean;

    @GetMapping("/user/login")
    public String ShowsignIn() {
        return "login";
    }


    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        if(userBean.getUsername().equals(username) &&  userBean.getPassword().equals(password)){
            System.out.println("Login thanh cong");
            return "index";
        }else{
            System.out.println("Login that bai");
        }
        return "login";

    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}
