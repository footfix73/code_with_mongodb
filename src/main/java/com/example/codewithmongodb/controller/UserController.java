package com.example.codewithmongodb.controller;

import com.example.codewithmongodb.domain.User;
import com.example.codewithmongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // Save method is predefine method in Mongo Repository
    // with this method we will save user in our database
    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userResponse = userRepo.save(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // findAll method is predefine method in Mongo Repository
    // with this method we will all user that is save in our database
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllFirstName/{firstName}")
    public ResponseEntity<List<User>> getAllFirstName(@PathVariable("firstName") String firstName) {
        return new ResponseEntity<>(userRepo.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/getAllFirstNameByLetter")
    public ResponseEntity<Map<String, Object>> getAllUsersPageByLetter(
            @RequestParam(required = true) String letter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        try {
            List<User> userList = new ArrayList<User>();
            Pageable paging = PageRequest.of(page, size);

            Page<User> pageTuts;
            pageTuts = userRepo.findUsersByFirstNameLetter(letter, paging);

            userList = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", userList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllLastName/{lastName}")
    public ResponseEntity<List<User>> getAllLastName(@PathVariable("lastName") String lastName) {
        return new ResponseEntity<>(userRepo.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsersPage(
            @RequestParam(required = false) String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "999") int size) {

        try {
            List<User> userList = new ArrayList<User>();
            Pageable paging = PageRequest.of(page, size);

            Page<User> pageTuts;
            if (firstName == null)
                pageTuts = userRepo.findAll(paging);
            else
                pageTuts = userRepo.findByFirstNameContainingIgnoreCase(firstName, paging);

            userList = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", userList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
