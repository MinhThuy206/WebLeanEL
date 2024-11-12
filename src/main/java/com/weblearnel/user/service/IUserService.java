package com.weblearnel.user.service;

import com.weblearnel.user.dto.request.CreateRequest;
import com.weblearnel.user.dto.request.SearchRequest;
import com.weblearnel.user.dto.response.PageResponse;
import com.weblearnel.user.dto.response.UserResponse;
import com.weblearnel.user.dto.response.DeleteResponse;
import com.weblearnel.user.dto.request.UpdateRequest;
import com.weblearnel.user.dto.response.UpdateResponse;
import org.springframework.stereotype.Service;


@Service
public interface IUserService {

    UserResponse getUser(Long id);

    UserResponse createUser(CreateRequest request);

    PageResponse<UserResponse> getUsers(SearchRequest request);

    DeleteResponse deleteUser(Long id);

    UpdateResponse<UserResponse> updateUser(Long id, UpdateRequest updateInfo);
}
