package com.example.releases.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Genres implements Comparable<Genres>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Genres(String name){
        this.name = name;
    }

    @Override
    public int compareTo(Genres g) {
        if(this.name.compareTo(g.getName()) > 0){
            return 1;
        }
        else if(this.name.compareTo(g.getName()) < 0){
            return -1;
        }
        else{
            return 0;
        }
    }
}
