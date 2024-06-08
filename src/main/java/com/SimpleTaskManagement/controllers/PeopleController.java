package com.SimpleTaskManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.services.PeopleService;

@Controller
public class PeopleController {
    private final PeopleService peopleService;
    
    @Autowired
    public PeopleController(PeopleService peopleService) {
	this.peopleService = peopleService;
    }
    
    @GetMapping("/registration")
    public String newPerson(@ModelAttribute("person") Person person) {
	return "registration";
    }
    
    @PostMapping("/registration")
    @ResponseBody
    public String registration(@ModelAttribute("person") Person person) {
	peopleService.addPerson(person);
	
	return "OK";
    }
    
    @GetMapping("/{id}/task_list")
    public void profilePage() {
	
    }
}
