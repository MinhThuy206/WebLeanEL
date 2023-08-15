package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.User;
import com.weblearnel.repository.UserRepository;
import com.weblearnel.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class UpdateProfileController {
    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    @GetMapping("/showViewUser/{user_id}")
    public String showView(@PathVariable("user_id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "account/overview";
    }

    @GetMapping("/update/{id}")
    public String viewProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/index"; // Return an error page if user not found
        }
        model.addAttribute("user", user);
        return "account/settings";
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody User newUser) {
        try {
            User user = userService.getUserByEmail(newUser.getEmail());
            System.out.println(newUser.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            user.setFullname(newUser.getFullname());
            user.setPhone(newUser.getPhone());
            user.setAddress(newUser.getAddress());
            userRepository.save(user);
            // User user = userService.getUserById(id);
            // if (user == null) {
            // return "index";
            // }
            // userService.updateUser(id, newUser);
            String redirectUrl = "/showViewUser/" + user.getId();
            return ResponseEntity.ok("redirect:" + redirectUrl);
        } catch (Exception e) {
            System.out.println("Cập nhật thất bại");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting the form: " + e.getMessage());
        }

    }
}
