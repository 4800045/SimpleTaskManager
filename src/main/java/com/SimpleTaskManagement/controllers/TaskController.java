package com.SimpleTaskManagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	
	return "redirect:/user/" + id;
	
	
    }
    
    
    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") int taskId, @RequestParam("userId") int userId) {
	taskService.deleteTask(taskId);
	return "redirect:/user/" + userId;
    }
    
    @GetMapping("/updateTask/{id}")
    public String updatePage(Model model, @PathVariable("id") int id) {
	
	Optional<Task> task = taskService.findOne(id);

	

	
	model.addAttribute("task", task.get());
	
	
	return "updatePage";
    }
    
    @PostMapping("/updateTask/update")
    public String updateTask(@ModelAttribute("task") Task task, @RequestParam("id") int id) {
	
	
	System.out.println("task from Model before update method//////////////////////////////");
	System.out.println(task);
	
	task = taskService.update(task, id);
		
	int userId = task.getPerformer().getPerson_id();
	
	return "redirect:/user/" + userId;
    }
    
}
