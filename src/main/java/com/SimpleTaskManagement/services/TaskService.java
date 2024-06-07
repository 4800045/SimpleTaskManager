package com.SimpleTaskManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimpleTaskManagement.models.Task;
import com.SimpleTaskManagement.repositories.TaskRepository;

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
	taskRepository.save(task);
    }
    
}
