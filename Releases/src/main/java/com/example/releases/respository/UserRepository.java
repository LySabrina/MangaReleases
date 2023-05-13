package com.example.releases.respository;

import com.example.releases.model.Genres;
import com.example.releases.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {


}
