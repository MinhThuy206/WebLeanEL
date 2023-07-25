package com.weblearnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.User;
import com.weblearnel.registration.RegistrationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class SignUpController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/users/showForm")
    public String showForm() {
        return "test";
    }

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
}
