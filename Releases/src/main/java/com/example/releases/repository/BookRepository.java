package com.example.releases.repository;

import com.example.releases.model.Book;
import com.example.releases.model.Genres;
import com.example.releases.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT * from BOOKS join books_genres IN :genres", nativeQuery = true)
    public List<Book> getBooksByGenres(@Param("genres") String[] genres);

    //Ever since Springboot 3.0, Springboot will convert the string into the Enum type
    @Query(value = "SELECT b from Book b JOIN b.genres JOIN Genres g where (YEAR(b.releaseDate)=:year OR :year IS NULL) AND (MONTHNAME(b.releaseDate)=:month OR :month IS NULL) AND (b.type IN (:formats) OR :formats IS NULL) AND (g.name IN (:genres) OR :genres IS NULL)")
    public List<Book> getFilteredBooks(@Param("year") Integer year, @Param("month") String month, @Param("formats") String[] formats, @Param("genres") String[] genres);

//    @Query(value = "SELECT * from Book where name like :query%", nativeQuery = true)
//    public List<Book> search(@Param("query") String query);


    @Query(value = "SELECT * FROM Book b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    public Page<Book> search(@Param("query") String query, Pageable pageable);


}
