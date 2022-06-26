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
import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userService;
	
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userService.findByEmail(username);
		 if (user.isPresent()) {
			 // User with the given email is found
			 return new MyUserDetails(user.get());
		}
		 else {
			 throw new UsernameNotFoundException("This email is not registered : "+username);
		 }
	
	}

    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(), registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()), "0");

        return userService.save(user);
    }

}
