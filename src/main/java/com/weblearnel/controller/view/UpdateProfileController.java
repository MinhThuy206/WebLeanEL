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


    @GetMapping("/showViewUser")
    public String showView(){
        return "account/overview";
    }

    @GetMapping("/update/{id}")
    public String viewProfile(@PathVariable Long id,  Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/index"; // Return an error page if user not found
        }
        model.addAttribute("user", user);
        return "account/settings";
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody User newUser) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "index";
        }
        userService.updateUser(id, newUser);
        return "redirect:/account/overview";
    }
}

