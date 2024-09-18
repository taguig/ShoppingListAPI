
package com.example.shoppingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapi.models.Person;
import com.example.shoppingapi.repositories.PersonRepository;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

@RequestMapping(path = "/person")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRep) {
        this.personRepository = personRep;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        return personRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        return personRepository.findById(id).map(Person -> {
            personRepository.delete(Person);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Person postMethodName(@RequestBody Person entity) {

        return personRepository.save(entity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> putMethodName(@PathVariable long id, @RequestBody Person entity) {

        return personRepository.findById(id).map(person -> {
            person.setId(entity.getId());
            person.setFirstName(entity.getFirstName());
            person.setLastName(entity.getLastName());
            person.setDateOfBirth(entity.getDateOfBirth());
            person.setEmail(entity.getEmail());

            return ResponseEntity.ok(personRepository.save(person));
        }).orElse(ResponseEntity.notFound().build());
    }

}
