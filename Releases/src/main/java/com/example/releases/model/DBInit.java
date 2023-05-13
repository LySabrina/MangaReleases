package com.example.releases.model;

import com.example.releases.respository.BookRepository;
import com.example.releases.respository.GenresRepository;
import com.example.releases.services.GenresService;
import com.example.releases.services.SevenSeaScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DBInit implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private GenresService service;
    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        genresRepository.deleteAll();
        List<Book> bookCollection = SevenSeaScraper.getBooks();
        Set<Genres> genresCollection = SevenSeaScraper.getGenres();
        if(!(bookCollection == null)){
            bookRepository.saveAll(bookCollection);
            for(Genres g : genresCollection){
                System.out.println(g.getName());    //error, it gives a constraint error when attempting to add a duplicated value
                service.saveGenre(g.getName());
            }
//            genresRepository.saveAll(genresCollection);
            System.out.println(":: DB INIT SUCCESSFUL");
        }
        else{
            System.out.println(":: DB INIT FAILED");
        }
    }
}
