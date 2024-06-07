package com.SimpleTaskManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimpleTaskManagement.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}