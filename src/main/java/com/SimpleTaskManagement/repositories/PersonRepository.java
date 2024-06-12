package com.SimpleTaskManagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.models.Task;

public interface PersonRepository extends JpaRepository<Person, Integer>{
    Optional<Person> findByUsername(String name);
    Optional<Person> findByEmail(String email);
    
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.tasks WHERE p.personId = :personId")
    Person fetchTasks(@Param("personId") int personId);

    

}
