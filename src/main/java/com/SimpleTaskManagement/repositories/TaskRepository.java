package com.SimpleTaskManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimpleTaskManagement.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
