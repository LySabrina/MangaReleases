package com.example.releases.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookGetAllResponse {
    private List<BookDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPageNo;
}
