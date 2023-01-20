package com.example.JavaProject.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private String emailAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person")
    private Person person;

    private String password;

    public Account() {}

    public Account(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }
    public  String getEmailAddress() {
        return emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
//    public Person getPerson() {
//        return person;
//    }
//    public void setPerson(Person person) {
//        this.person =person;
//    }
}
