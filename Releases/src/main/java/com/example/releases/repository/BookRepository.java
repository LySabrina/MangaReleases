package com.example.releases.repository;

import com.example.releases.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //GET BOOKS BY DATE
    @Query(value = "Select * from Book where YEAR(release_date) = :year AND MONTHNAME(release_date) = :month", nativeQuery = true)
    public List<Book> getBooksByDate(@Param("year") int year, @Param("month") String month);

    //GET BOOKS BY DATE AND GENRE AND FORMAT
    @Query(value = "SELECT * from Book WHERE YEAR(release_date) = :year AND MONTHNAME(release_date) = :month AND type IN :formats", nativeQuery = true)
    public List<Book> getBookByDateGenre(@Param("year") int year, @Param("month") String month,
                                         @Param("formats") String[] formats);

    //GET BOOKS BY FORMATS
    @Query(value = "SELECT * FROM BOOK WHERE type IN :formats ", nativeQuery = true)
    public List<Book> findBooksMatchingFormat  (@Param("formats") String[] formats);


    //gets all the genres associated with the book
    @Query(value = "SELECT genres.name from books_genres JOIN genres on genres_id = id where book_id = :bookId ", nativeQuery = true)
    public List<String> getBookGenres(@Param("bookId") Long bookId);

    @Query(value = "Select * from book where name = :bookName", nativeQuery = true)
    public Book getBookByName(@Param("bookName") String bookName);


    //Cats as a genre with an id of 1. i want to get all books associated with that genre
    @Query(value = "SELECT * from BOOKS join books_genres IN :genres", nativeQuery = true)
    public List<Book> getBooksByGenres(@Param("genres") String[] genres);

    // i got the genres, formats, year and month,
    //i should handle situations where:
    /*
        - select only format for ALL books
        - select only genres for ALL books
        - select both format and genres for ALL books
        - select format and date
        - select genre and date
        - select both format and genre and date
     */

    //MAKE A FUNCTION THAT HAS OPTINAL PARAMETERS genres, formats, year, and month

    @Query(value = "SELECT * from ", nativeQuery = true)
    public List<Book>  getFilteredBooks();



}
