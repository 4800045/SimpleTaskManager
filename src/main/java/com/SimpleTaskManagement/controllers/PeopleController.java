package com.SimpleTaskManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;
import com.SimpleTaskManagement.security.PersonDetails;
import com.SimpleTaskManagement.services.PeopleService;
import com.SimpleTaskManagement.services.TaskService;
import com.SimpleTaskManagement.util.UserValidation;

import jakarta.validation.Valid;

@Controller
public class PeopleController {
    private final PeopleService peopleService;
    private final UserValidation validation;
    private final TaskService taskService;
    
    @Autowired
    public PeopleController(PeopleService peopleService, TaskService taskService, UserValidation validation) {
	this.peopleService = peopleService;
	this.taskService = taskService;
	this.validation = validation;
    }
    
    @GetMapping("/hello")
    public String helloPage() {
	return "helloPage";
    }
    
    @GetMapping("/registration")
    public String newPerson(@ModelAttribute("person") Person person) {
	return "registration";
    }
    
    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
	validation.validate(person, bindingResult);
	
	if (bindingResult.hasErrors()) {
	    return "registration";
	}
	else {
	    peopleService.addPerson(person);
	}
	
	int id = person.getPerson_id();
	
	
	
	return "redirect:/user/" + id;
    }
    
    
    @GetMapping("/info") 
    public String info() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	 PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
	 
	 return "hello";
    }
    
    @GetMapping("/loginSuccess")
    public RedirectView loginSuccess(RedirectAttributes attributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
            
            if (personDetails.getPerson().getRole().equals("ROLE_ADMIN")) {
                return new RedirectView("/admin");
            }
            
            int userId = personDetails.getPerson().getPerson_id();
            
            attributes.addAttribute("id", userId);
            
            return new RedirectView("/user/{id}");
        }
        
        return new RedirectView("/login");
    }


    
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") int id, Model model, @ModelAttribute("task") Task task) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	if (authentication.isAuthenticated()) {
	    
	    
	    PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
	    
	    int currentId = personDetails.getPerson().getPerson_id();
	    
	    String role = personDetails.getPerson().getRole();
	    
	    
	    if (currentId == id || role.equals("ROLE_ADMIN")) {
		
		
		List<Task> tasks = taskService.TaskListForPerson(id);
		
		model.addAttribute("tasks", tasks);
		model.addAttribute("userId", id);
		
		
		return "userPage";
	    }
	}
	
	return "accessDenied";
    }
    
    
    
    
}
