package com.example.releases.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String reviewedBy;
    private String bookReviewed;
}
