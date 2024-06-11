package com.SimpleTaskManagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;

public interface PersonRepository extends JpaRepository<Person, Integer>{
    Optional<Person> findByUsername(String name);
    Optional<Person> findByEmail(String email);
    

}
