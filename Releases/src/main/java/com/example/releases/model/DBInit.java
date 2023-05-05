package com.example.releases.model;

import com.example.releases.respository.BookRepository;
import com.example.releases.services.SevenSeaScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component

public class DBInit implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;


    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Set<Book> bookCollection = SevenSeaScraper.getBooks();
        if(!(bookCollection == null)){
            for(Book b: bookCollection){
                bookRepository.save(b);
            }
            System.out.println(":: DB INIT SUCCESSFUL");
        }
        else{
            System.out.println(":: DB INIT FAILED");
        }
    }
}
