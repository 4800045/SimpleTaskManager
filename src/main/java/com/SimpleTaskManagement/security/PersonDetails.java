package com.SimpleTaskManagement.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SimpleTaskManagement.models.Person;

public class PersonDetails implements UserDetails{
    
    private final Person person;
    
    public PersonDetails(Person person) {
	this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
	return this.person.getPassword();
    }

    @Override
    public String getUsername() {
	return this.person.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	
	String status = this.person.getStatus();
	
	return status.equals("active");
	
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getEmail() {
	return this.person.getEmail();
    }
    
    public Person getPerson() {
	return this.person;
    }
}
