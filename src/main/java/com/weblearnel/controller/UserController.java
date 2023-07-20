package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

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

    @GetMapping("/")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}

