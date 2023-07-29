package com.weblearnel.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weblearnel.model.User;
import com.weblearnel.repository.UserRepository;
import com.weblearnel.service.ForgotService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


// @RestController
@Controller
@AllArgsConstructor
public class ForgotController {
    @Autowired
    private ForgotService forgotService;
    private UserRepository userRepository;
    private final Map<String, Integer> otpStorage = new HashMap<>();
    
    // render forgot password page
    @GetMapping("/forgot-password")
    public String showForm() {
        return "forgot";
    }

    // send otp to email, lưu email - otp vào session
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        int otp = new Random().nextInt(10001, 999999);
        model.addAttribute("email", email);
        otpStorage.put(email, otp);
        return forgotService.sendOTP(email, otp);
        // return "verify_otp";
    }
    // lấy otp trong session, verify otp gửi email cho reset password html 
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam("otp") int otp, HttpServletRequest request, Model model)  {
        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();
        int sent_otp = otpStorage.get(email);
        if (otp == sent_otp) {
            model.addAttribute("userEmail", email);
            return "resetPassword";
        } else {
            return "verify_otp";
        }
    }

    // update password gọi user từ email, set password mới, save user
    @PostMapping("/updatePassword")
    public String updatePassword(
        @RequestParam("newPassword") String password, 
        @RequestParam("confirmPass") String confirmPassword, 
        HttpServletRequest request) 
        {

        if (password.equals(confirmPassword)) {
            HttpSession session = request.getSession();
            String email = session.getAttribute("email").toString();
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                User user1 = user.get();
                user1.setPassword(password);
                userRepository.save(user1);
            }
            return "login";
        } else {
            return "resetPassword";
        }
        
    }


}