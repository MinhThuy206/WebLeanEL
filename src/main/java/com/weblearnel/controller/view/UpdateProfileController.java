package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@Controller
@RequestMapping
public class UpdateProfileController {
    @Autowired
    private UserService userService;


    @GetMapping("/showViewUser/{user_id}")
    public String showView(@PathVariable("user_id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
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

