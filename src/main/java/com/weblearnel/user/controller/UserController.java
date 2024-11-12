package com.weblearnel.user.controller;

import java.util.List;

import com.weblearnel.user.dto.request.CreateRequest;
import com.weblearnel.user.dto.request.SearchRequest;
import com.weblearnel.user.dto.request.UpdateRequest;
import com.weblearnel.user.dto.response.DeleteResponse;
import com.weblearnel.user.dto.response.PageResponse;
import com.weblearnel.user.dto.response.UpdateResponse;
import com.weblearnel.user.dto.response.UserResponse;
import com.weblearnel.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weblearnel.user.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public UserResponse createUser(@RequestBody CreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/")
    public PageResponse<UserResponse> getUsers(@RequestBody SearchRequest request) {
        return userService.getUsers(request);
    }

    @PutMapping("/{id}")
    public UpdateResponse<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}

