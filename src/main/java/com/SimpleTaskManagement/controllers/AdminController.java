package com.SimpleTaskManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.repositories.PersonRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public AdminController(PersonRepository personRepository) {
	this.personRepository = personRepository;
    }
    
    @GetMapping()
    public String adminPage(Model model) {
	List<Person> personList = personRepository.findAll();
	
	model.addAttribute("personList", personList);
	
	return "adminPage";
    }
    
}
