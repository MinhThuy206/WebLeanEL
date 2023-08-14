package com.weblearnel.controller.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.weblearnel.model.User;
import com.weblearnel.repository.UserRepository;
import com.weblearnel.service.ForgotService;

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
        // return "forgot";
        return "authentication/password-reset";
    }

    @GetMapping("/verify-otp")
    public String verifyOTP() {
        return "authentication/two-steps";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "authentication/new-password";
    }

    // send otp to email, lưu email - otp vào session
    // public String sendOTP(@RequestParam("email") String email, Model model,
    // HttpServletRequest request) {
    // HttpSession session = request.getSession();
    // session.setAttribute("email", email);
    // int otp = new Random().nextInt(100001, 999999);
    // model.addAttribute("email", email);
    // otpStorage.put(email, otp);
    // return forgotService.sendOTP(email, otp);
    // // return "verify_otp";
    // }
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody User user, HttpSession session) {
        try {
            String email = user.getEmail();
            User userEmailCheck = userRepository.findByEmail(email).get();
            if (userEmailCheck != null) {
                int otp = new Random().nextInt(100001, 999999);
                session.setAttribute("email", email);
                otpStorage.put(email, otp);
                forgotService.sendOTP(email, otp);
                return ResponseEntity.ok("redirect:/verify-otp");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: " + "Email không tồn tại");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: " + e.getMessage());
        }
    }

    // @GetMapping("/reset-password")
    // public String resetPasswordPage() {
    // return "authentication/new-password";
    // }
    // lấy otp trong session, verify otp gửi email cho reset password html
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(Model model, HttpSession session,
            @RequestBody Map<String, Object> requestBody) {
        try {
            // HttpSession session = request.getSession();
            String email = session.getAttribute("email").toString();
            int sent_otp = otpStorage.get(email);
            int enteredOtp = Integer.parseInt(requestBody.get("otp").toString());
            if (enteredOtp == sent_otp) {
                model.addAttribute("userEmail", email);
                return ResponseEntity.ok("redirect:/reset-password");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error submitting the form: " + "Mã OTP không đúng");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: " + e.getMessage());
        }

    }

    // update password gọi user từ email, set password mới, save user
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(HttpSession session, @RequestBody Map<String, Object> requestBody) {
        try {
            String email = session.getAttribute("email").toString();
            Optional<User> user = userRepository.findByEmail(email);
            String password = requestBody.get("password").toString();
            String confirmPassword = requestBody.get("confirmPassword").toString();

            if(requestBody.containsKey("currentPassword")) {
                String currentPassword = requestBody.get("currentPassword").toString();
                if (user.isPresent()) {
                    User user1 = user.get();
                    if (user1.getPassword().equals(currentPassword)) {
                        
                        if (password.equals(confirmPassword)) {
                            user1.setPassword(password);
                            userRepository.save(user1);
                            return ResponseEntity.ok("redirect:/user/login");
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body("Error submitting the form: " + "confirm password không đúng");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error submitting the form: " + "current password không đúng");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error submitting the form: " + "user không tồn tại");
                }
            }
            // String password = requestBody.get("password").toString();
            // String confirmPassword = requestBody.get("confirmPassword").toString();
            if (password.equals(confirmPassword)) {
                // session = request.getSession();
                // String email = session.getAttribute("email").toString();
                // Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) {
                    User user1 = user.get();
                    user1.setPassword(password);
                    userRepository.save(user1);
                }
                return ResponseEntity.ok("redirect:/user/login");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error submitting the form: " + "confirm password không đúng");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: " + e.getMessage());
        }
        

    }

}