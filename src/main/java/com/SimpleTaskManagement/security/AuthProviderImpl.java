package com.SimpleTaskManagement.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.SimpleTaskManagement.models.Person;
import com.SimpleTaskManagement.services.PersonDetailsService;


@Component
public class AuthProviderImpl implements AuthenticationProvider{
    
    private final PersonDetailsService personDetailsService;
    
    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
	this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String name = authentication.getName();
	
	String password = authentication.getCredentials().toString();
	
	UserDetails personDetails = personDetailsService.loadUserByUsername(name);
	
	if (!personDetails.getPassword().equals(password)) {
	    throw new BadCredentialsException("Incorrect password");
	}
	
	
	return new UsernamePasswordAuthenticationToken(personDetails, password, personDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
	return true;
    }

}
