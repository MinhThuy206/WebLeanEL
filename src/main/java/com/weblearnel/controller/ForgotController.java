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

// import com.weblearnel.service.ForgotService;

// @RestController
@Controller
@AllArgsConstructor
public class ForgotController {
    @Autowired
    private ForgotService forgotService;
    private UserRepository userRepository;
    // private UserService userService;
    private final Map<String, Integer> otpStorage = new HashMap<>();
    
    // render forgot password page
    @GetMapping("/forgot-password")
    public String showForm() {
        return "forgot";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        int otp = new Random().nextInt(10001, 999999);
        // model.addAttribute("sent_otp", otp);
        model.addAttribute("email", email);
        otpStorage.put(email, otp);
        return forgotService.sendOTP(email, otp);
        // return "verify_otp";
    }
    
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam("otp") int otp, HttpServletRequest request, Model model)  {
        // int temp = model.getAttribute("sent_otp").hashCode();
        // int sent_otp = Integer.parseInt(request.getParameter("sent_otp"));
        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();
        int sent_otp = otpStorage.get(email);
        if (otp == sent_otp) {
            model.addAttribute("userEmail", email);
            return "resetPassword";
        } else {
            return "verify_otp";
        }
        // System.out.println("OTP: " + otp);
        // return "reset_password";
    }

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
        // System.out.println("Password: " + password);
        // System.out.println("Confirm Password: " + confirmPassword);
        // return "login";
    }


}