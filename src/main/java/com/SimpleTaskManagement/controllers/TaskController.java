package com.SimpleTaskManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SimpleTaskManagement.services.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;
    
    
    @Autowired
    public TaskController(TaskService taskService) {
	this.taskService = taskService;
    }
    
    
    @GetMapping("/new_task")
    public void newTaskPage() {
	
    }
    
    
}
