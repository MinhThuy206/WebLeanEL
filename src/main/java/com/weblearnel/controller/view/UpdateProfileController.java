package com.weblearnel.controller.view;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UpdateProfileController {
    @Autowired
    private UserService userService;


    @GetMapping("/update/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/index"; // Return an error page if user not found
        }
        model.addAttribute("user", user);
        return "account/settings";
    }

    @PostMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @ModelAttribute User updatedUser) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "index"; // Return an error page if user not found
        }

        userService.updateUser(id, updatedUser);
        return "redirect:/account/overview";
    }
}

