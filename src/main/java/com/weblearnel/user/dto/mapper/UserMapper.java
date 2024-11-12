package com.weblearnel.user.dto.mapper;

import com.weblearnel.user.dto.response.UserResponse;
import org.mapstruct.Mapper;
import com.weblearnel.user.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> content);
}
