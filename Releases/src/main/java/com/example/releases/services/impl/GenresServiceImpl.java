package com.example.releases.services.impl;

import com.example.releases.repository.GenresRepository;
import com.example.releases.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresServiceImpl implements GenresService {

    private GenresRepository genresRepository;

    @Autowired
    public GenresServiceImpl(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public List<String> getAllGenres() {
        return genresRepository.getAllGenresName();
    }
}
