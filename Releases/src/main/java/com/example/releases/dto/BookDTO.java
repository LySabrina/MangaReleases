package com.example.releases.dto;

import com.example.releases.model.Type;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    private String name;
    private String series;
    private Type type;

    private String author;
    private String artist;

    private double price;

    private String ISBN;

    private LocalDate releaseDate;
    private String imagePath;
}
