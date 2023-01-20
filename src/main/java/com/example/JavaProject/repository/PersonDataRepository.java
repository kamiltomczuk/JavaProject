package com.example.JavaProject.repository;

import com.example.JavaProject.model.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {
    PersonData findPersonDataByPhoneNumber(String phoneNumber);
    PersonData findPersonDataByAddress(String address);
}
