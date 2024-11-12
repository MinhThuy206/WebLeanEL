package com.weblearnel.user.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int currentPageSize;
    private List<T> data;
}
