package com.example.releases.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String series;

    @Column(columnDefinition = "ENUM('MANGA', 'NOVEL', 'MANHWA', 'OEL')")
    @Enumerated(EnumType.STRING)
    private Type type;

    private String author;
    private String artist;

    private double price;

    private String ISBN;

    private LocalDate releaseDate;
    private String imagePath;

    public Book(String name, String series, Type type, String author, String artist, double price, String ISBN, LocalDate releaseDate, String imagePath) {
        this.name = name;
        this.series = series;
        this.type = type;
        this.author = author;
        this.artist = artist;
        this.price = price;
        this.ISBN = ISBN;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
    }
}
