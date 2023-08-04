package com.weblearnel.controller.view;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UpdateProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/update")
    public String updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(user, id);
        return "updateProfile";
    }
}
