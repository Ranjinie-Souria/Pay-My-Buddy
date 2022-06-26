package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openclassrooms.PayMyBuddy.dto.UserRegistrationDto;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.MyUserDetailsService;
import com.openclassrooms.PayMyBuddy.service.UserService;



@Controller
public class UserController {
	
	@Autowired
	private MyUserDetailsService userDetail;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
    public String home() {
		System.out.println(userService.getUsers());
        return "home";
    }
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
	public String login() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "register";
	}
	
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
    @RequestMapping(value = "/registration", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
    	userDetail.save(registrationDto);
    	return "redirect:/registration?success";
    }
	
	@GetMapping("/profile")
	public String profile() {
		System.out.println("Hello user");
		return "home";
	}

}
