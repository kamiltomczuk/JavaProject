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
public class PersonDataService {
    PersonDataRepository personDataRepository;

    AccountRepository accountRepository;

    PersonRepository personRepository;
    private String errorMessage;

    @Autowired
    public PersonDataService(PersonDataRepository personDataRepository, AccountRepository accountRepository, PersonRepository personRepository) {
        this.personDataRepository = personDataRepository;
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public List<PersonData> getAllPersonData() {
        if(this.personDataRepository.findAll().isEmpty()) {
            throw new EmptyListException("List of Person's Data is empty");
        }
        return this.personDataRepository.findAll();
    }

    public PersonData getPersonDataById(long id) {
        return this.personDataRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no PersonData with id" + id));
    }

    public PersonData getPersonDataByPhoneNumber(String phoneNumber) {
        if(this.personDataRepository.findPersonDataByPhoneNumber(phoneNumber)==null) {
            throw new NotFoundException("There is no PersonData with phone number" + phoneNumber);
        }
        return this.personDataRepository.findPersonDataByPhoneNumber(phoneNumber);
    }

    public PersonData getPersonDataByAddress(String address) {
        if(this.personDataRepository.findPersonDataByAddress(address)==null) {
            throw new NotFoundException("There is no PersonData with " + address + " address");
        }
        return this.personDataRepository.findPersonDataByAddress(address);
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public PersonData addPersonData(PersonData personData) {

        return this.personDataRepository.save(personData);
    }

    public PersonData updatePersonData(PersonData personData, long id) {
        PersonData personDataExists = this.personDataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no PersonData with id: " + id));

        personDataExists.setPhoneNumber(personData.getPhoneNumber());
        personDataExists.setAddress(personData.getAddress());
        return this.personDataRepository.save(personDataExists);
    }

    public void deletePersonData(long id) {
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
