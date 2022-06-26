package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class ConnectionController {
	
	
	@Autowired
	private UserService userService;
	
	private MyUserDetails userDetails;
	private String currentUserEmail;
	
	public void setCurrentUser(Authentication authentication) {
		this.userDetails = (MyUserDetails) authentication.getPrincipal();
		this.currentUserEmail = userDetails.getEmail();
	}
	
	@GetMapping("/connection")
    public String showConnections(Authentication authentication) {
		setCurrentUser(authentication);
		System.out.println(currentUserEmail);
		System.out.println(userService.getConnectionsForEmail(currentUserEmail));
        return "connectionsHome";
    }
	
	@PostMapping("/connection/add")
    public String addConnection() {
		
        return "home";
    }
	
	

}
