package com.weblearnel.user.service.impl;

import com.weblearnel.user.dto.mapper.UserMapper;
import com.weblearnel.user.dto.request.CreateRequest;
import com.weblearnel.user.dto.request.SearchRequest;
import com.weblearnel.user.dto.request.UpdateRequest;
import com.weblearnel.user.dto.response.DeleteResponse;
import com.weblearnel.user.dto.response.PageResponse;
import com.weblearnel.user.dto.response.UpdateResponse;
import com.weblearnel.user.dto.response.UserResponse;
import com.weblearnel.user.repo.UserRepo;
import com.weblearnel.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.weblearnel.user.entity.User;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse getUser(Long id) {
        return userMapper.toUserResponse(userRepo.findById(id).orElse(null));
    }

    @Override
    public UserResponse createUser(CreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setMobile(request.getMobile());
        user.setFullname(request.getFullname());
        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public PageResponse<UserResponse> getUsers(SearchRequest request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPage());
        Page<User> users = userRepo.getUsersByConditions(request.getUsername() + "%", request.getEmail() + "%", pageable);
        PageResponse<UserResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotalPages(users.getTotalPages());
        pageResponse.setTotalElements(users.getTotalElements());
        pageResponse.setCurrentPage(users.getNumber());
        pageResponse.setCurrentPageSize(users.getSize());
        pageResponse.setData(userMapper.toUserResponseList(users.getContent()));
        return pageResponse;
    }

    @Override
    public DeleteResponse deleteUser(Long id) {
        DeleteResponse deleteResponse = new DeleteResponse();
        if (!userRepo.existsById(id)) {
            deleteResponse.setMessage("User not found");
            return deleteResponse;
        }
        userRepo.deleteById(id);
        deleteResponse.setMessage("User deleted successfully");
        return deleteResponse;
    }

    @Override
    public UpdateResponse<UserResponse> updateUser(Long id, UpdateRequest updateInfo) {
        UpdateResponse<UserResponse> response = new UpdateResponse<UserResponse>();
        if (!userRepo.existsById(id)) {
            response.setMessage("User not found");
            return response;
        }
        User user = userRepo.findById(id).orElse(null);
        if(!user.getUsername().equals(updateInfo.getUsername()) && !updateInfo.getUsername().isEmpty()) {
            user.setUsername(updateInfo.getUsername());
        }
        if(!user.getPassword().equals(updateInfo.getPassword()) && !updateInfo.getPassword().isEmpty()) {
            user.setPassword(updateInfo.getPassword());
        }
        if(!user.getEmail().equals(updateInfo.getEmail()) && !updateInfo.getEmail().isEmpty()) {
            user.setEmail(updateInfo.getEmail());
        }
        if(!user.getAddress().equals(updateInfo.getAddress()) && !updateInfo.getAddress().isEmpty()) {
            user.setAddress(updateInfo.getAddress());
        }
        if(!user.getMobile().equals(updateInfo.getMobile()) && !updateInfo.getMobile().isEmpty()) {
            user.setMobile(updateInfo.getMobile());
        }
        if(!user.getFullname().equals(updateInfo.getFullname()) && !updateInfo.getFullname().isEmpty()) {
            user.setFullname(updateInfo.getFullname());
        }
        response.setData(userMapper.toUserResponse(userRepo.save(user)));
        return response;
    }
}
