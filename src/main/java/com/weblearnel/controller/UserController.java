package com.weblearnel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@Controller
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
//    @GetMapping("/{id}")
//    public User findUser(@PathVariable long id) {
//        return userService.getOneUser(id);
//    }

    // update user
    @PostMapping("/update")
    public String updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(user, id);
        return "updateProfile";
    }

    // delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

}

