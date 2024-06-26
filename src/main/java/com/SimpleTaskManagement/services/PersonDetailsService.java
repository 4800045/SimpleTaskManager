package com.SimpleTaskManagement.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.repositories.PersonRepository;
import com.SimpleTaskManagement.security.PersonDetails;


@Service
public class PersonDetailsService implements UserDetailsService{

    private final PersonRepository personRepository;
    
    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
	this.personRepository = personRepository;
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<Person> person = personRepository.findByUsername(username);
	
	if (person.isEmpty()) {
	    person = personRepository.findByEmail(username);
	    if (person.isEmpty()) {
		throw new UsernameNotFoundException("User not found");
	    }
	}
	
	return new PersonDetails(person.get());
	
    }
    
    
}
