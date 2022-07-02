package com.openclassrooms.PayMyBuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class ConnectionController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/connection")
    public String showConnections(Authentication authentication,ModelMap model) {
		AddHTML.addFooterHeader(model);
		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
		System.out.println(currentUser.getEmail());
		List<String> connections = userService.getConnectionsForEmail(currentUser.getEmail());
		System.out.println(connections);
		model.addAttribute("connection", connections);
        return "connectionsHome";
    }
	
    @RequestMapping(value = "/connection", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addConnection(Authentication authentication,@RequestParam String email) {
    	MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    	userService.addConnectionForEmail(currentUser.getEmail(), email);
    	return "redirect:/connection?success";
    }

	
	

}
