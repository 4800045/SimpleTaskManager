package security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SimpleTaskManagement.models.Person;

public class PersonDetails implements UserDetails{
    
    private Person person;
    
    public PersonDetails(Person person) {
	this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
	return person.getPassword();
    }

    @Override
    public String getUsername() {
	return person.getUsername();
    }

}
