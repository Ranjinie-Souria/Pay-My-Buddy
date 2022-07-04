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
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class ConnectionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping("/connection")
    public String showConnections(Authentication authentication,ModelMap model) {
		AddHTML.addFooterHeader(model);
		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
		List<User> connections = userService.getConnectionsForEmail(currentUser.getEmail());		
		model.addAttribute("connection", connections);
		//List<Transaction> transactions = transactionService.getTransactionsForEmail(currentUser.getEmail());
		//System.out.println(transactions);
		//model.addAttribute("transaction", transactions);
        return "connectionsHome";
    }
	
    @RequestMapping(value = "/connection", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addConnection(Authentication authentication,@RequestParam String email) {
    	MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    	int success = userService.addConnectionForEmail(currentUser.getEmail(), email);
    	if(success == 1) {
    		return "redirect:/connection?success";
    	}
    	else if(success == 0) {
    		return "redirect:/connection?SameEmailError";
    	}
    	else if(success == 2) {
    		return "redirect:/connection?UserNotFound";
    	}
    	else {
    		return "redirect:/connection?UnknownError";
    	}
		
    	
    }

	
	

}
