package com.example.releases.respository;

import com.example.releases.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenresRepository extends JpaRepository<Genres, Long> {
    /**
     * Used to check if the genre exists and fetches the data
     * @param name the name of the genre
     * @return Genre the genre from the database else null
     */
    @Query(value = "SELECT * from genres where name = :name", nativeQuery = true)
    public Genres doesGenreExist( @Param("name")String name);

    @Query(value = "SELECT name from genres", nativeQuery = true)
    public List<String> getAllGenresName();

    @Query(value = "Select name, id from genres", nativeQuery = true)
    public List<String> getAllGenres();
}
