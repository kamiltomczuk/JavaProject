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
public class AccountService {
    AccountRepository accountRepository;
    PersonDataRepository personDataRepository;

    PersonRepository personRepository;

    private String errorMessage;

    @Autowired
    public AccountService(AccountRepository accountRepository, PersonDataRepository personDataRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personDataRepository = personDataRepository;
        this.personRepository = personRepository;
    }

    public List<Account> getAllAccounts() {
        if(this.accountRepository.findAll().isEmpty()) {
            throw new EmptyListException("List of accounts is empty");
        }
        return this.accountRepository.findAll();
    }

    public Account getAccountById(long id){
        return this.accountRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no account with id:" + id));
    }

    public Account getAccountByEmail(String emailAddress){
        if(this.accountRepository.findAccountByEmailAddress(emailAddress)==null){
            throw new NotFoundException("There is no account with email address:" + emailAddress);
        }
        return this.accountRepository.findAccountByEmailAddress(emailAddress);
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public Account addAccount(Account account){
        return this.accountRepository.save(account);
    }

    public Account updateAccount(Account account,long id) {

        Account accountExists = this.accountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no account with id:" + id));
        accountExists.setEmailAddress(account.getEmailAddress());
        accountExists.setPassword(account.getPassword());
        return this.accountRepository.save(accountExists);
    }

    public void deleteAccount(long id) {
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
