package com.example.JavaProject.service;


import com.example.JavaProject.exceptions.EmptyListException;
import com.example.JavaProject.exceptions.NotFoundException;
import com.example.JavaProject.exceptions.NotValidException;
import com.example.JavaProject.model.Account;
import com.example.JavaProject.model.Person;
import com.example.JavaProject.model.PersonData;
import com.example.JavaProject.repository.AccountRepository;
import com.example.JavaProject.repository.PersonDataRepository;
import com.example.JavaProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepository;
    PersonDataRepository personDataRepository;

    AccountRepository accountRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonDataRepository personDataRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.personDataRepository = personDataRepository;
        this.accountRepository = accountRepository;
    }


    private String errorMessage;

    public List<Person> getAllPeople() {
        if(this.personRepository.findAll().isEmpty()) {
            throw new EmptyListException("List of people is empty");
        }
        return this.personRepository.findAll();
    }

    public Person getPersonById(long id) {
        return this.personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no person with id: " + id));
    }

    public List<Person> getPersonByName(String name) {
        if(this.personRepository.findPersonByName(name).isEmpty()) {
            throw new NotFoundException("There is no person with name: " + name);
        }
        return this.personRepository.findPersonByName(name);
    }



    public String getErrorMessage() {
        return errorMessage;
    }





    public Person addPerson(Person person) {

        return this.personRepository.save(person);
    }

    public Person updatePerson(Person person, long id) {

        Person personExists = this.personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no person with id:" + id));
        personExists.setName(person.getName());
        personExists.setAge(person.getAge());


        return this.personRepository.save(personExists);
    }

    public void deletePerson(long id) {

        Person personExists = this.personRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no person with id:" + id));

        List<Account> accountExists = personExists.getAccounts();
        for(Account account : accountExists) {
            this.accountRepository.delete(account);
        }
        PersonData personDataExists = personExists.getPersonData();
        this.personDataRepository.delete(personDataExists);

        this.personRepository.delete(personExists);
    }


}
