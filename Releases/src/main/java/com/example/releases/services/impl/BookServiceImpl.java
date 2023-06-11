package com.example.releases.services.impl;

import com.example.releases.dto.BookDTO;
import com.example.releases.dto.mapper.BookMapper;
import com.example.releases.exceptions.BookNotFoundException;
import com.example.releases.model.Book;
import com.example.releases.model.Type;
import com.example.releases.respository.BookRepository;
import com.example.releases.services.BookService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service    //Spring can register this and bring all your beans
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private BookMapper bookMapper;
    @Autowired  //put @Autowired near the constructor for future unit testing in the future and best practices
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        //Easier way to do this instead of using a for-each loop, looks cleaner too
        return allBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByDate(int year, String month) {
        List<Book> allBooks = bookRepository.getBooksByDate(year,month);
        return allBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByDateGenre(int year, String month, String[] formats) {
        List<Book> allBooks = bookRepository.getBookByDateGenre(year, month, formats);
        return allBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBooksMatchingFormat(String[] formats) {
        List<Book> allBooks = bookRepository.findBooksMatchingFormat(formats);
        return allBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getFilteredBooks(Integer year, String month, String[] formats, String[] genres) {
        List<BookDTO> allBooksDTO = new ArrayList<>();
        if(year == null && month == null && genres == null ){
            //meaning format was only picked
            List<Book> allBooks = bookRepository.findBooksMatchingFormat(formats);
            for(Book b: allBooks){
                BookDTO bookDTO = bookMapper.INSTANCE.mapToBookDTO(b);
                allBooksDTO.add(bookDTO);
            }
            return allBooksDTO;
        }
        else if(year == null && month == null & formats == null){
            List<Book> allBooks = bookRepository.getBooksByGenres(genres);
            for(Book b: allBooks){
                BookDTO bookDTO = bookMapper.INSTANCE.mapToBookDTO(b);
                allBooksDTO.add(bookDTO);
            }
            return allBooksDTO;
        }
        return null;
    }

    @Override
    public ResponseEntity<String> getBookImage(Long id)  throws IOException{

        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book Image with ID is not found"));
        if(book == null){
            return ResponseEntity.notFound().build();
        }
        String imagePath = book.getImagePath();
        File image = new File(imagePath + ".jpg");      //might need to add an extensions when saving manga from Sevenseas
        byte[] imageContents = FileUtils.readFileToByteArray(image);
        String encodedFile = Base64.getEncoder().encodeToString(imageContents);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(encodedFile);
    }

    @Override
    public List<String> getBookGenres(Long id) {
        return bookRepository.getBookGenres(id);
    }

    @Override
    public Type[] getAllFormats() {
        return Type.values();
    }


}
