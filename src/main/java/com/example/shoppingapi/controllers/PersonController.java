package com.example.shoppingapi.controllers;

import com.example.shoppingapi.models.Person;
import com.example.shoppingapi.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

}
