package com.weblearnel.controller;

import com.weblearnel.model.User;
import com.weblearnel.registration.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/user/login")
    public String ShowsignIn() {
        return "login";
    }


    @PostMapping("/checklogin")
    public String checkMapping(){
        return "view-user";
    }

}
