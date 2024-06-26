package com.SimpleTaskManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;
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
	Optional<Person> person = personRepository.findById(id);
	List<Task> taskList = person.get().getTasks();
	
	return person;
    }
    
    public Optional<Person> findByName(String name) {
	Optional<Person> person = personRepository.findByUsername(name);
	
	return person;
    }
    
    @Transactional
    public void updatePerson(Person person) {
	Optional<Person> personToBeUpdated = personRepository.findById(person.getPerson_id());
	
	personToBeUpdated.get().setStatus(person.getStatus());
	
	personRepository.save(personToBeUpdated.get());
	
    }
    
    @Transactional
    public void addPerson(Person person) {
	
	person.setRole("ROLE_USER");
	
	personRepository.save(person);
    }
    
    
    
    
}
