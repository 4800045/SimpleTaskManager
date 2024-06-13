package com.SimpleTaskManagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.repositories.PersonRepository;
import com.SimpleTaskManagement.services.PeopleService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final PersonRepository personRepository;
    
    private final PeopleService peopleService;
    
    @Autowired
    public AdminController(PersonRepository personRepository, PeopleService peopleService) {
	this.personRepository = personRepository;
	this.peopleService = peopleService;
    }
    
    @GetMapping()
    public String adminPage(Model model) {
	List<Person> personList = personRepository.findAll();
	
	model.addAttribute("personList", personList);
	
	return "adminPage";
    }
    
    @PostMapping("/setUserStatus/{id}")
    public String setUsetStatus(@PathVariable("id") int userId) {
	Optional<Person> person = peopleService.findOne(userId);
	
	
	if (person.get().getStatus().equals("active")) {
	    person.get().setStatus("deactivated");
	}
	else {
	    person.get().setStatus("active");
	}
	
	peopleService.updatePerson(person.get());
	
	return "redirect:/admin";
	
	
	
	
    }
}
