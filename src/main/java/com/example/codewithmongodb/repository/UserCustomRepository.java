package com.example.codewithmongodb.repository;

import com.example.codewithmongodb.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {

    Page<User> findUsers(Pageable pageable);

    Page<User> findUsersByFirstNameLetter(String letter, Pageable pageable);

}
