package com.example.releases.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
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


    @ManyToMany
    @JsonIgnore
    @JoinTable(name="books_genres",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "genres_id")
                )
    private List<Genres> genres = new ArrayList<>();


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

    @Override
    public boolean equals(Object o){
        Book b  = (Book)o;
        if(this.name.equals(b.getName())){
            return true;
        }
        else{
            return false;
        }
    }

}
