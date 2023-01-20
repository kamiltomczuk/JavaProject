package com.example.JavaProject.web;

import com.example.JavaProject.model.Account;
import com.example.JavaProject.model.Person;
import com.example.JavaProject.repository.PersonRepository;
import com.example.JavaProject.service.PersonDataService;
import com.example.JavaProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    PersonService personService;
    PersonDataService personDataService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable(value ="name") String name) {
        return ResponseEntity.ok(personService.getPersonByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePerson(@RequestBody Person person, @PathVariable ("id") long id) {
        personService.updatePerson(person, id);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable(value = "id") long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person with id: " + id + " is now deleted");
    }
}
