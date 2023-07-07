package com.example.releases.services.impl;

import com.example.releases.dto.BookDTO;
import com.example.releases.dto.BookGetAllResponse;
import com.example.releases.dto.mapper.BookMapper;
import com.example.releases.exceptions.BookNotFoundException;
import com.example.releases.model.Book;
import com.example.releases.model.Type;
import com.example.releases.repository.BookRepository;
import com.example.releases.services.BookService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public BookGetAllResponse getAllBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);

        List<Book> listOfBook = books.getContent();
        List<BookDTO> content = listOfBook.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());

        BookGetAllResponse bookGetAllResponse = new BookGetAllResponse();
        bookGetAllResponse.setContent(content);
        bookGetAllResponse.setPageNo(books.getNumber());
        bookGetAllResponse.setPageSize(books.getSize());
        bookGetAllResponse.setTotalPageNo(books.getTotalPages());
        bookGetAllResponse.setTotalElements(books.getTotalElements());

        return bookGetAllResponse;

    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book ID could not be found"));
        return bookMapper.INSTANCE.mapToBookDTO(book);
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

    @Override
    public List<BookDTO> getFilteredBooks(Integer year, String month, String[] formats, String[] genres) {
        List<BookDTO> allBooksDTO = new ArrayList<>();
        List<Book> getFilteredBooks = bookRepository.getFilteredBooks(year, month, formats, genres);
        return getFilteredBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> search(String query) {
        return null;
    }


//    @Override
//    public List<BookDTO> search(String query) {
//        List<Book> allBooks = bookRepository.search(query);
//        return allBooks.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());
//    }

    @Override
    public BookGetAllResponse search(String query, int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<Book> books = bookRepository.search(query, page);

        List<Book> listOfBook = books.getContent();
        List<BookDTO> content = listOfBook.stream().map(book -> bookMapper.INSTANCE.mapToBookDTO(book)).collect(Collectors.toList());

        BookGetAllResponse bookGetAllResponse = new BookGetAllResponse();
        bookGetAllResponse.setContent(content);

        bookGetAllResponse.setPageNo(books.getNumber());
        bookGetAllResponse.setPageSize(books.getSize());
        bookGetAllResponse.setTotalPageNo(books.getTotalPages());
        bookGetAllResponse.setTotalElements(books.getTotalElements());

        return bookGetAllResponse;

    }
}
