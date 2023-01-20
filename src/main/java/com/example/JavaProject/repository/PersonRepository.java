package com.example.JavaProject.repository;

import com.example.JavaProject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByName(String name);
    List<Person> findPersonByNameAndAgeEquals(String name, int age);
}
