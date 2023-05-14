package com.example.releases.respository;

import com.example.releases.model.Book;
import com.example.releases.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "Select * from Book where YEAR(release_date) = :year AND MONTHNAME(release_date) = :month", nativeQuery = true)
    public List<Book> getBooksByDate(@Param("year") int year, @Param("month") String month);

    @Query(value = "SELECT * from Book WHERE YEAR(release_date) = :year AND MONTHNAME(release_date) = :month AND type IN :formats", nativeQuery = true)
    public List<Book> getBookByDateGenre(@Param("year") int year, @Param("month") String month,
                                         @Param("formats") String[] formats);

    @Query(value = "SELECT * FROM BOOK WHERE type IN :formats ", nativeQuery = true)
    public List<Book> findBooksMatchingFormat  (@Param("formats") String[] formats);
}
