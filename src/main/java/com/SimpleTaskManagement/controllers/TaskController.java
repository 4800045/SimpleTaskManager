package com.SimpleTaskManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;
import com.SimpleTaskManagement.security.PersonDetails;
import com.SimpleTaskManagement.services.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;
    
    
    @Autowired
    public TaskController(TaskService taskService) {
	this.taskService = taskService;
    }
    
    
    @PostMapping("/newTask")
    public String newTaskPage(@ModelAttribute("task") Task task) {
	
	taskService.addTask(task);
	
	int id = task.getPerformer().getPerson_id();
	
	return "redirect:/user/{id}";
	
	
    }
    
    
}
