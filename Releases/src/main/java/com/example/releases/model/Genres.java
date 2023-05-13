package com.example.releases.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Genres implements Comparable<Genres>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)

    private String name;

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
