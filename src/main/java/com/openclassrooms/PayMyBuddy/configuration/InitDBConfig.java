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
		System.out.println("initialisation de la base de données");
    	Optional<User> user = userService.getUserByEmail("admin@admin.com");
    	if(user.isEmpty()) {
    		UserRegistrationDto registrationDto = new UserRegistrationDto("admin", "admin@admin.com", "admin");
    		userDetail.save(registrationDto);
    	}
	}

}
