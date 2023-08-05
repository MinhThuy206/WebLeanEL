package com.weblearnel.controller.view;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UpdateProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/{userId}/edit")
    public String showUpdateProfileForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "updateProfile"; // Trả về tên của view (template) "updateProfileForm.html"
    }

    @PostMapping("/users/{userId}/edit")
    public String updateProfile(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        userService.updateUser(updatedUser, userId);
        return "index"; // Chuyển hướng về trang hiển thị thông tin hồ sơ sau khi cập nhật
    }
}
