package com.SimpleTaskManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PeopleService {
    private final PersonRepository personRepository;
    
    @Autowired
    public PeopleService(PersonRepository personRepository) {
	this.personRepository = personRepository;
    }
    
    public List<Person> PeopleList() {
	return personRepository.findAll();
    }
    
    public Optional<Person> findOne(int id) {
	return personRepository.findById(id);
    }
    
    @Transactional
    public void addPerson(Person person) {
	personRepository.save(person);
    }
}
