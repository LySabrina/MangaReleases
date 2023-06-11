package com.example.releases.services.impl;

import com.example.releases.dto.BookDTO;
import com.example.releases.model.Book;
import com.example.releases.respository.BookRepository;
import com.example.releases.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service    //Spring can register this and bring all your beans
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired  //put @Autowired near the constructor for future unit testing in the future and best practices
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookDTO> allBooksDTO = new ArrayList<>();
        for(Book b : allBooks){
            BookDTO bookDTO = new BookDTO();
            //Maybe find a way to use MapStruct to map easier than manually in future updates
            bookDTO.setId(b.getId());
            bookDTO.setName(b.getName());
            bookDTO.setSeries(b.getSeries());
            bookDTO.setType(b.getType());
            bookDTO.setAuthor(b.getAuthor());
            bookDTO.setArtist(b.getArtist());
            bookDTO.setPrice(b.getPrice());
            bookDTO.setISBN(b.getISBN());
            bookDTO.setReleaseDate(b.getReleaseDate());
            bookDTO.setImagePath(b.getImagePath());

            allBooksDTO.add(bookDTO);
        }
        return allBooksDTO;
    }
}
