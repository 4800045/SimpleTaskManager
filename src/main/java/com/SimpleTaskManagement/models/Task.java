package com.SimpleTaskManagement.models;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "start_date")
    private LocalDateTime startDate;
    
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "status")
    private String status;
    
    
    @ManyToOne
    @JoinColumn(name = "performerid", referencedColumnName = "personId")
    private Person performer;
    
    public Task() {}
    

    public Task(String name, String description, String status,
	    Person performer) {
	this.name = name;
	this.description = description;
	this.status = status;
	this.performer = performer;
    }

    public int getId() {
        return task_id;
    }

    public void setId(int id) {
        this.task_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerformer() {
        return performer;
    }

    public void setPerformer(Person performer) {
        this.performer = performer;
    }


    @Override
    public String toString() {
	return "Task [task_id=" + task_id + ", name=" + name + ", description=" + description + ", startDate="
		+ startDate + ", endDate=" + endDate + ", status=" + status + "]";
    }
    
    
    
    
    
    
}
