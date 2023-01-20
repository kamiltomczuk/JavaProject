package com.example.JavaProject.web;


import com.example.JavaProject.model.Account;
import com.example.JavaProject.model.Person;
import com.example.JavaProject.repository.AccountRepository;
import com.example.JavaProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }



    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/find/emailAddress/{emailAddress}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable(value = "emailAddress")String emailAddress) {
        return ResponseEntity.ok(accountService.getAccountByEmail(emailAddress));
    }


    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.addAccount(account));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAccount(@RequestBody Account account, @PathVariable ("id") long id) {
        accountService.updateAccount(account, id);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(value = "id") long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with id:" + id + "is now deleted");
    }


}
