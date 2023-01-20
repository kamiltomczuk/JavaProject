package com.example.JavaProject.web;



import com.example.JavaProject.model.PersonData;
import com.example.JavaProject.repository.PersonDataRepository;
import com.example.JavaProject.service.PersonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PersonDatas")
public class PersonDataController {
    PersonDataService personDataService;

    @Autowired
    public PersonDataController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonData>> getAllPersonData() {
        return ResponseEntity.ok(personDataService.getAllPersonData());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<PersonData> getPersonDataById(@PathVariable(value="id") long id) {
        return ResponseEntity.ok(personDataService.getPersonDataById(id));
    }

    @GetMapping("/find/phoneNumber/{phoneNumber}")
    public ResponseEntity<PersonData> getPersonDataByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(personDataService.getPersonDataByPhoneNumber(phoneNumber));
    }

    @PostMapping("/add")
    public ResponseEntity<PersonData> addPersonData(@RequestBody PersonData personData) {
        return ResponseEntity.ok(personDataService.addPersonData(personData));
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<PersonData> updatePersonData(@RequestBody PersonData personData, @PathVariable ("id") long id) {
        return ResponseEntity.ok(personDataService.updatePersonData(personData,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersonData(@PathVariable (value = "id") long id) {
        personDataService.deletePersonData(id);
        return ResponseEntity.ok("Person with id: " + id + "is now deleted");
    }
}
