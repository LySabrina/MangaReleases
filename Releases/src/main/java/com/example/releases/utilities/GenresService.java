package com.example.releases.utilities;

import com.example.releases.model.Genres;
import com.example.releases.repository.GenresRepository;
import org.springframework.stereotype.Service;

@Service
public class GenresService {

    private final GenresRepository genreRepository;

    public GenresService(GenresRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void saveGenre(String name) {
        Genres g = genreRepository.doesGenreExist(name);
        if(g == null){
            System.out.println("GENRE IS NULL, hence genre does not exist");
            Genres newGenre = new Genres(name);
            System.out.println("ADDING GENRE TO DB");
            genreRepository.save(newGenre);
        }
        else{
            System.out.println("GENRE EXISTS - WILl NOT BE ADDED TO DB");

        }
    }
}
