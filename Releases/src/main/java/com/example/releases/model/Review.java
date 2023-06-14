package com.example.releases.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //A review will be associated with one book
    //A review will be associated with one user
    //A user will have made multiple reviews
    //A book will have multiple reviews
    //Each review is distinct

    private String reviewedBy;
    private String bookReviewed;
}
