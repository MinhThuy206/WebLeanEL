package com.weblearnel.user.dto.request;

import com.weblearnel.utils.Pagination;
import lombok.Data;

@Data
public class SearchRequest extends Pagination {
    String username;
    String email;
}
