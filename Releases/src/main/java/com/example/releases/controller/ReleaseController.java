package com.example.releases.controller;

import com.example.releases.model.Book;
import com.example.releases.model.Type;
import com.example.releases.respository.BookRepository;
import com.example.releases.respository.GenresRepository;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;



//@RestController tells SpringBoot to convert Java Object to JSON (ex. Book is converted to JSON format when you getBookByID)
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReleaseController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenresRepository genresRepository;

    //by default, get all the books and display it

    @GetMapping("/")
    public List<Book> getAllBooks(){
        //let us get all the books from the database. Look at each of its imagePath
        //From the imagePath
       return (List<Book>) bookRepository.findAll();
    }

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
        List<String> allGenres = genresRepository.getAllGenresName();
        return  allGenres;
    }

    @GetMapping("/formats")
    public Type[] getAllFormats(){
        return Type.values();
    }

    @GetMapping("{id}/image")
    public ResponseEntity<String> getImage(@PathVariable Long id) throws IOException {
        Book book = bookRepository.getReferenceById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }
        String imagePath = book.getImagePath();
        File image = new File(imagePath + ".jpg");      //might need to add an extensions when saving manga from Sevenseas
        byte[] imageContents = FileUtils.readFileToByteArray(image);
        String encodedFile = Base64.getEncoder().encodeToString(imageContents);
         return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(encodedFile);
    }

    @GetMapping("/date")
    public List<Book> getBooksByDate(@RequestParam int year, @RequestParam String month){
        return bookRepository.getBooksByDate(year, month);
    }

}
