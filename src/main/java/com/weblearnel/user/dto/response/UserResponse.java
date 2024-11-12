package com.weblearnel.user.dto.response;

import com.weblearnel.model.Log;
import com.weblearnel.model.Result;
import com.weblearnel.model.Topic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String fullname;
    private String address;
    private String mobile;
    private String email;
    private Integer role;
    private String token;
    private Boolean enabled;
    private Integer level;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Set<Log> logs;
    private Set<Result> results;
    private Set<Topic> topics;
}
