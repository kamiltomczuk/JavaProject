package com.example.JavaProject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persondata")
public class PersonData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable=false)
    private String address;

//    @OneToOne
//    @JoinColumn(name="person")
//    private Person person;

    public PersonData() {}
    public PersonData(String phoneNumber, String address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
}
