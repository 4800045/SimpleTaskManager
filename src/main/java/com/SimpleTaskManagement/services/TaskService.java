package com.SimpleTaskManagement.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;
import com.SimpleTaskManagement.repositories.TaskRepository;
import com.SimpleTaskManagement.security.PersonDetails;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    
    @Autowired
    public TaskService(TaskRepository taskRepository) {
	this.taskRepository = taskRepository;
    }
    
    public List<Task> taskList() {
	return taskRepository.findAll();
    }
    
    public Optional<Task> findOne(int id) {
	return taskRepository.findById(id);
    }
    
    @Transactional
    public void addTask(Task task) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
	
	Person person = personDetails.getPerson();
	
	task.setStartDate(LocalDateTime.now());
	
	task.setEndDate(LocalDateTime.now().plusDays(7));
	
	person.getTasks().add(task);
	
	task.setPerformer(person);
	
	taskRepository.save(task);
    }
    
    
    public List<Task> TaskListForPerson(int id) {
	List<Task> taskList = taskRepository.findByPerformerPersonId(id);
	
	return taskList;
    }
    
    
    
}
