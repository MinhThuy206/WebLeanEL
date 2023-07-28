package com.weblearnel.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import com.weblearnel.service.ForgotService;

// @RestController
@Controller
public class ForgotController {
    // @Autowired
    // // private ForgotService forgotService;
    // private EmailService emailService;

    @GetMapping("/forgot-password")
    public String showForm() {
        return "forgot";
    }

    // @PostMapping("/forgot-password")
    // public String forgotPass(@RequestParam String email) {
    // String response = forgotService.forgotPass(email);

    // if (!response.startsWith("Invalid")) {
    // response = "http://localhost:8086/reset-password?token=" + response;
    // }
    // return response;
    // }

    // @PutMapping("/reset-password")
    // public String resetPass(@RequestParam String token, @RequestParam String
    // password) {
    // return forgotService.resetPass(token, password);
    // }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email) {
        System.out.println("Email: " + email);
        // generate otp
        int otp = new Random().nextInt(10001, 999999);

        System.out.println("OTP: " + otp);

        return "verify_otp";
    }

}