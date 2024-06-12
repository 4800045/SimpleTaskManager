package com.SimpleTaskManagement.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personid")
    private int personId;
    
    @Column(name = "username")
    @Size(min = 4, max = 30, message = "Username should be between 4 and 30 characters")
    private String username;
    
    @Column(name = "email")
    @Email
    private String email;
    
    @Column(name = "password")
    @NotEmpty
    private String password;
    
    @Column(name = "role")
    private String role;

    
    @OneToMany(mappedBy = "performer")
    private List<Task> tasks;
    
    
    public Person() {}

    public Person(String username, String email, String password) {
	this.username = username;
	this.email = email;
	this.password = password;
    }

    
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPerson_id() {
        return personId;
    }

    public void setPerson_id(int person_id) {
        this.personId = person_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", username='" + username + '\'' +
                ", email=" + email +
                ", role='" + role + '\'' +
                '}';
    
    }
    
}
