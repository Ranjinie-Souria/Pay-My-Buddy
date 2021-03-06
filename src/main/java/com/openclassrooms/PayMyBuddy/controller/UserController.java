package com.openclassrooms.PayMyBuddy.controller;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String home(ModelMap model) {
		System.out.println(userService.getUsers());
		AddHTML.addFooterHeader(model);		
        return "home";
    }
	
	@GetMapping("/login")
	public String showLoginForm(ModelMap model) {
		AddHTML.addFooterHeader(model);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
	public String login(ModelMap model) {
		AddHTML.addFooterHeader(model);
		return "login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(ModelMap model) {
		AddHTML.addFooterHeader(model);
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
    	String email = registrationDto.getEmail();
    	String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    	 
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(email);
    	if(matcher.matches()){
    	    Optional<User> user = userService.getUserByEmail(email);
	    	if(user.isEmpty()) {
	    		userDetail.save(registrationDto);
	    		return "redirect:/login?success";
	    	}
	    	else {
	    		return "redirect:/registration?DuplicateEmail";
	    	}
    	}
    	else {
    		return "redirect:/registration?IncorrectEmail";
    	}
    	

    	
    }
	
	@GetMapping("/profile")
	public String profile(ModelMap model) {
		AddHTML.addFooterHeader(model);
		return "home";
	}

}
