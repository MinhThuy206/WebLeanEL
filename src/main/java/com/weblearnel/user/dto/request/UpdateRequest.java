package com.weblearnel.user.dto.request;

import lombok.Data;

@Data
public class UpdateRequest {
    String username;
    String password;
    String fullname;
    String address;
    String mobile;
    String email;
}
