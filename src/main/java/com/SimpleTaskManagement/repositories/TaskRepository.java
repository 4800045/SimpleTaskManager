package com.SimpleTaskManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimpleTaskManagement.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    List<Task> findByPerformerPersonId(int performerId);
}
