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
	Optional<Person> optionalPerson = personRepository.findByUsername(username);
	
	if (optionalPerson.isEmpty()) {
	    throw new UsernameNotFoundException("User not found");
	}
	
	Person person = optionalPerson.get();
	
	// TODO figure it out later
	
	
	return null;
	
	
    }

}
