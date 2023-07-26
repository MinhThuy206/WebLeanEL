package com.weblearnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.User;
import com.weblearnel.model.Word;
import com.weblearnel.registration.RegistrationService;
import com.weblearnel.service.WordService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class SignUpController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private WordService wordService;

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
    @GetMapping("/admin/createWord")
    public String wordForm() {
        return "wordForm";
    }

    @PostMapping("/submitWord")
    public String submitWordForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String mean = request.getParameter("mean");
        String attributes = request.getParameter("attributes");
        String example = request.getParameter("example");
        String imageUrl = "static/images/" + name + ".jpg";
        String pronounce = "static/audio/" + name + ".mp3";
        Word word = new Word(name, mean, attributes, example, imageUrl, pronounce);
        wordService.addWord(word);
        return "redirect:/admin/createWord";
    }

}
