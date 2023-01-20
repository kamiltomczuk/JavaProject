package com.example.JavaProject.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @OneToMany
    @JoinColumn(name="person")
    private List<Account> accounts;

    @OneToOne
    @JoinColumn(name="persondata")
    private PersonData personData;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "person_account",
//    joinColumns = @JoinColumn(name="account_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name="person_id", referencedColumnName = "id"))
//    private List<Account> accounts;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "person_persondata",
//    joinColumns = @JoinColumn(name="persondata_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name="person_id", referencedColumnName = "id"))
//    private PersonData personDatas;

    public Person() {}
    public Person(String name, int age, List<Account> accounts) {
        this.name = name;
        this.age = age;
        this.accounts= accounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Account> getAccounts(){
        return accounts;
    }
    public void setAccount(List<Account> accounts) {
        this.accounts = accounts;
    }

    public PersonData getPersonData() {
        return personData;
    }
    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }


}