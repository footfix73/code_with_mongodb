package com.example.codewithmongodb.repository;

import com.example.codewithmongodb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {
    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);

    Page<User> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<User> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
