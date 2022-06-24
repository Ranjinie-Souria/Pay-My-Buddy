package com.openclassrooms.PayMyBuddy.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.dto.UserRegistrationDto;
import com.openclassrooms.PayMyBuddy.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserService userService;
	
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
				
		Optional<User> user = userService.getUserByEmail(email);
		

		 if (user.isPresent()) {
			 return new org.springframework.security.core.userdetails.User(
					 user.get().getEmail(),
					 user.get().getPassword(),
					 Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); 
		}
		 else {
			 throw new UsernameNotFoundException("This email is not registered : "+email);
		 }
	
	}

    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(), registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()), "0");

        return userService.saveUser(user);
    }

}
