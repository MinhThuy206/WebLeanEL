package com.weblearnel.utils;

import lombok.Data;

import java.util.List;

@Data
public class Pagination {
    private int page;
    private int pageSize;
    private String sort;
    private String direction;

    public int getOffset() {
        return page * pageSize;
    }

    public <T> List<T> getPage(List<T> data) {
        int start = getOffset();
        int end = Math.min(start + pageSize, data.size());
        return data.subList(start, end);
    }
}
