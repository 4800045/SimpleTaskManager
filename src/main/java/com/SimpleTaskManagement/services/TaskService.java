package com.SimpleTaskManagement.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;
import com.SimpleTaskManagement.repositories.PersonRepository;
import com.SimpleTaskManagement.repositories.TaskRepository;
import com.SimpleTaskManagement.security.PersonDetails;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;
    
    @Autowired
    public TaskService(TaskRepository taskRepository, PersonRepository personRepository) {
	this.taskRepository = taskRepository;
	this.personRepository = personRepository;
	
    }
    
    public List<Task> taskList() {
	return taskRepository.findAll();
    }
    
    public Optional<Task> findOne(int id) {
	return taskRepository.findById(id);
    }
    
    @Transactional
    public void addTask(Task task, int id) {
	
	Optional<Person> optionalPerson = personRepository.findById(id);
	
	Person person = optionalPerson.get();
	
	task.setStartDate(LocalDateTime.now());
	
	task.setEndDate(LocalDateTime.now().plusDays(7));
	
	task.setStatus("new");
	
	person = personRepository.fetchTasks(person.getPerson_id());
	
	task.setPerformer(person);
	
	taskRepository.save(task);
    }
    
    
    public List<Task> TaskListForPerson(int id) {
	List<Task> taskList = taskRepository.findByPerformerPersonId(id);
	
	return taskList;
    }
    
    @Transactional
    public void deleteTask(int id) {
	taskRepository.deleteById(id);;
    }
    
    @Transactional
    public Task update(Task task, int id) {
	System.out.println(task);
	
	
	Optional<Task> taskToBeUpdated = taskRepository.findById(id);
	
	System.out.println(taskToBeUpdated.get());
	
	
	taskToBeUpdated.get().setName(task.getName());
	taskToBeUpdated.get().setDescription(task.getDescription());
	
	System.out.println(taskToBeUpdated.get());
	
	taskRepository.save(taskToBeUpdated.get());
	
	return taskToBeUpdated.get();
    }
    
    
}
