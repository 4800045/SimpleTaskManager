package com.SimpleTaskManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SimpleTaskManagement.security.AuthProviderImpl;
import com.SimpleTaskManagement.services.PersonDetailsService;

@Configuration
public class SecurityConfig {
    
    private final PersonDetailsService personDetailsService;
    private final PassConfig passConfig;
    
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, PassConfig passConfig) {
	this.personDetailsService = personDetailsService;
	this.passConfig = passConfig;
    }
    
    
    
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(passConfig.getPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.csrf(csrf -> csrf.disable())
	.authorizeHttpRequests(authorize -> authorize
		.requestMatchers("/admin").hasRole("ADMIN")
		.requestMatchers("/registration").permitAll()
		.anyRequest().hasAnyRole("USER", "ADMIN")
		
	)
	.formLogin(formLogin -> formLogin
		.defaultSuccessUrl("/loginSuccess", true));	
		
	return http.build();
    }
    
    
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//	AuthenticationManagerBuilder authenticationManagerBuilder = 
//		http.getSharedObject(AuthenticationManagerBuilder.class);
//	
//	authenticationManagerBuilder
//		.userDetailsService(userDetailsService);
//	
//	return authenticationManagerBuilder.build(); 	
		
//    }
    
    

}
