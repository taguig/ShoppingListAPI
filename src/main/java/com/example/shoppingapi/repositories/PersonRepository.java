package com.example.shoppingapi.repositories;

import com.example.shoppingapi.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * example jpql & native query
     */
    @Query(value = "select * from person", nativeQuery = true)
    public List<Person> findAllPersons();

}
