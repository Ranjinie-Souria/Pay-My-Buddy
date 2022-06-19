package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.UserService;



@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
    public String home() {
		System.out.println(userService.getUsers());
        return "home";
    }
	
	@GetMapping("/register")
	public String register(@RequestBody User person) {
		userService.saveUser(person);
		System.out.println(userService.getUserById(0));
		return "home";
	}

}
