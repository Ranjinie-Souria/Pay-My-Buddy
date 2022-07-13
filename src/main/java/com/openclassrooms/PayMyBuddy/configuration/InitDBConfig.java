package com.openclassrooms.PayMyBuddy.configuration;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.dto.UserRegistrationDto;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.MyUserDetailsService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Service
public class InitDBConfig {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MyUserDetailsService userDetail;

	@PostConstruct
	public void initializeDB() {
		System.out.println("Creating Database");
    	Optional<User> user = userService.getUserByEmail("admin@admin.com");
    	if(user.isEmpty()) {
    		System.out.println("Creating Admin account");
    		UserRegistrationDto registrationDto = new UserRegistrationDto("admin", "admin@admin.com", "admin");
    		userDetail.save(registrationDto);
    	}
    	Optional<User> userA = userService.getUserByEmail("a@email.com");
    	if(userA.isEmpty()) {
    		System.out.println("Creating User A account");
    		UserRegistrationDto registrationDto = new UserRegistrationDto("a", "a@email.com", "a");
    		userDetail.save(registrationDto);
    	}
    	Optional<User> userB = userService.getUserByEmail("b@email.com");
    	if(userB.isEmpty()) {
    		System.out.println("Creating User B account");
    		UserRegistrationDto registrationDto = new UserRegistrationDto("b", "b@email.com", "b");
    		userDetail.save(registrationDto);
    	}
	}

}
