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

    // get all users
    @GetMapping("/")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    // find user by id
    @GetMapping("/{id}")
    public User findUser(@PathVariable long id) {
        return userService.getOneUser(id);
    }

    // update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.updateUser(user, id);
    }

    // delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}

