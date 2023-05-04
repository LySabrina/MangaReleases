package com.example.releases.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String series;

    @Column(columnDefinition = "ENUM('MANGA', 'LIGHT_NOVEL', 'MANHWA', 'OEL')")
    @Enumerated(EnumType.STRING)
    private Type type;

    private String author;
    private String artist;

    private double price;

    private String ISBN;

    private LocalDate releaseDate;


}
