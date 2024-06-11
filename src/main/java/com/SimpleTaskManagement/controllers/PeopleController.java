package com.SimpleTaskManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class PeopleController {
    private final PeopleService peopleService;
    
    private final TaskService taskService;
    
    @Autowired
    public PeopleController(PeopleService peopleService, TaskService taskService) {
	this.peopleService = peopleService;
	this.taskService = taskService;
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
    @ResponseBody
    public String registration(@ModelAttribute("person") Person person) {
	
	peopleService.addPerson(person);
	
	return "OK";
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
	    
	    
	    
	    attributes.addAttribute("userId", userId);
	    
	    
	    return new RedirectView("/user/" + userId);
	}
	
	return new RedirectView("/login");
    }
    
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") int id, Model model, @ModelAttribute("task") Task task) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	if (authentication.isAuthenticated()) {
	    PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
	    
	    int currentId = personDetails.getPerson().getPerson_id();
	    
	    if (currentId == id) {
		
		List<Task> tasks = taskService.TaskListForPerson(currentId);
		
		model.addAttribute("tasks", tasks);
		
		return "userPage";
	    }
	}
	
	return "accessDenied";
    }
    
    
}
