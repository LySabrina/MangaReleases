package com.example.releases.controller;

import com.example.releases.dto.BookDTO;
import com.example.releases.dto.BookGetAllResponse;
import com.example.releases.model.Type;
import com.example.releases.repository.BookRepository;
import com.example.releases.repository.GenresRepository;
import com.example.releases.services.BookService;
import com.example.releases.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * TODO:
 */

//@RestController tells SpringBoot to convert Java Object to JSON (ex. Book is converted to JSON format when you getBookByID)
@RestController //Spring will automatically convert your return object into a ResponseEntity with all the status code and default headers
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenresService genresService;

    @GetMapping("/")
    public BookGetAllResponse getAllBooks(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return bookService.getAllBooks(pageNo, pageSize);
    }

    //TEST LATER
    @GetMapping("/BookDetails/{id}")
    public BookDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    //Scratch that, I am implementing Reviews so we need to have a Get
//    //No need for the API endpoint because it's better for the client-side to do the routing
//    //At initial launch ("/"), we sent the front-end application ALL the BOOK DATA hence it's holding the data already
//     There's no need for it to make another HTTP REQUEST (which takes time) to get data when it already have it
//    @GetMapping("/BookDetails/{id}")
//    public Book getBookById(@PathVariable Long id){
//        Optional<Book> bookResponse = bookRepository.findById(id);
//        Book book = bookResponse.get();
//        return book;
//    }

    @GetMapping("/genres")
    public List<String> getAllGenres(){
        return genresService.getAllGenres();
    }


    @GetMapping("/formats")
    public Type[] getAllFormats(){
        return bookService.getAllFormats();
    }

    @GetMapping("{id}/image")
    public ResponseEntity<String> getImage(@PathVariable Long id) throws IOException {
        return bookService.getBookImage(id);
    }

    @GetMapping("/date")
    public List<BookDTO> getBooksByDate(@RequestParam int year, @RequestParam String month){
        return bookService.getBooksByDate(year, month);
    }

    @GetMapping("/dateGenre")
    public List<BookDTO> getBooksByDateGenre(@RequestParam int year, @RequestParam String month, @RequestParam String[] formats){
        return bookService.getBooksByDateGenre(year,month,formats);
    }

    @GetMapping("/getFormats")
    public List<BookDTO> findBooksMatchingFormat(@RequestParam String[] formats){
        return bookService.findBooksMatchingFormat(formats);
    }

    @GetMapping("{id}/genres")
    public List<String> getBookGenres(@PathVariable Long id){
            return bookService.getBookGenres(id);
    }


    //formats, genres
    @GetMapping("/filters")
    public List<BookDTO> getFilteredBooks(@RequestParam(required = false) Integer year, @RequestParam(required = false) String month, @RequestParam(required = false) String[] formats, @RequestParam(required = false) String[] genres){
        //now i need to know which of these are toggled
          /*
        - select only format for ALL books
        - select only genres for ALL books
        - select both format and genres for ALL books
        - select format and date
        - select genre and date
        - select both format and genre and date
     */
        return bookService.getFilteredBooks(year, month, formats, genres);
    }

    @GetMapping("/search")
    public List<BookDTO> search(@RequestParam(required = true) String query){
        return bookService.search(query);
    }

}
