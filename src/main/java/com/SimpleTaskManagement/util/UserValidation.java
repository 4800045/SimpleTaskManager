package com.SimpleTaskManagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.services.PeopleService;


@Component
public class UserValidation implements Validator{
    
    private final PeopleService peopleService;
    
    @Autowired
    public UserValidation(PeopleService peopleService) {
	this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
	
	return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Person person = (Person) target;
	
	if(peopleService.findByName(person.getUsername()).isPresent()) {
	    errors.rejectValue("username", "", "User is already exist");
	}
    }

}
