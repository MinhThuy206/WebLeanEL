package com.weblearnel.user.dto.response;

import lombok.Data;

@Data
public class UpdateResponse<T> {
    private String message;
    private T data;
}
