package com.weblearnel.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

// @Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Add user
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // get all users
    @GetMapping("/")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }


    // update user
    @PostMapping("/update")
    public String updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "updateProfile";
    }

    // delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

}

