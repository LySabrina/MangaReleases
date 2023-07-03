package com.example.releases.services;

import com.example.releases.dto.BookDTO;
import com.example.releases.dto.BookGetAllResponse;
import com.example.releases.model.Book;
import com.example.releases.model.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface BookService {

    public BookGetAllResponse getAllBooks(int pageNo, int pageSize);

    public BookDTO getBookById(Long id);

    public List<BookDTO> getBooksByDate(@RequestParam int year, @RequestParam String month);

    public List<BookDTO> getBooksByDateGenre(@RequestParam int year, @RequestParam String month, @RequestParam String[] formats);

    public List<BookDTO> findBooksMatchingFormat(@RequestParam String[] formats);

    public List<BookDTO> getFilteredBooks(@RequestParam(required = false) Integer year, @RequestParam(required = false) String month, @RequestParam(required = false) String[] formats, @RequestParam(required = false) String[] genres);

    public ResponseEntity<String> getBookImage(@PathVariable Long id) throws IOException;

    public List<String> getBookGenres(@PathVariable Long id);

    public Type[] getAllFormats();

    public List<BookDTO> search(@RequestParam(required = true) String query);
}
