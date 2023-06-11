package com.example.releases.utilities;

import com.example.releases.model.Book;
import com.example.releases.model.Genres;
import com.example.releases.respository.BookRepository;
import com.example.releases.respository.GenresRepository;
import com.example.releases.utilities.GenresService;
import com.example.releases.utilities.SevenSeaScraper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @Transactional
    /**
     * @Transactional - ensures there is a session available when accessing a collection and contineus to allow lazy loading
     * Why use this? When I have all the tables dropped then run the program, I get an error [failed to lazily initialize a collection of role: com.example.releases.model.Genres.books: could not initialize proxy - no Session]
     * This occurs because I have no existing database schema and data. Hibernate attemps to lazily load collections but there are no records from the table and data (since it has been dropped)
     *
     * The error does not occur when there existing table because Hibernate can lazily load collections since there is a table schema
     */

    /**
     * Runs an initial init to insert some data to the database
     */
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        genresRepository.deleteAll();
        HashMap<Book, List<Genres>> bookCollection = SevenSeaScraper.getHashBooks();
        Set<Genres> collection = SevenSeaScraper.getGenres();

        for(Genres genre: collection){
            service.saveGenre(genre.getName());
        }

        for(Map.Entry<Book, List<Genres>> element : bookCollection.entrySet()){
            Book book = element.getKey();
            List<Genres> genres = element.getValue();
            for(Genres g : genres){
                Genres persistedGenre = genresRepository.doesGenreExist(g.getName());
                book.getGenres().add(persistedGenre);
            }
            bookRepository.save(book);
        }
        System.out.println(":: DB INIT FINISHED");
    }
}
