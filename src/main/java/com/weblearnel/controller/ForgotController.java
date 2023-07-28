package com.weblearnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.service.ForgotService;

@RestController
// @Controller
public class ForgotController {
    @Autowired
    private ForgotService forgotservice;

    // @GetMapping("/showForm")
    // public String showForm() {
    // return "forgot";
    // }

    @PostMapping("/forgot-password")
    public String forgotPass(@RequestParam String email) {
        String response = forgotservice.forgotPass(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8082/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPass(@RequestParam String token, @RequestParam String password) {
        return forgotservice.resetPass(token, password);
    }

}