package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}

