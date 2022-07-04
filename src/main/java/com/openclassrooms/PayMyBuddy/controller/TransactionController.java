package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionService tService;
	
    @RequestMapping(value = "/transaction", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addTransaction(Authentication authentication,@RequestParam String email,@RequestParam String amount,@RequestParam String description) {
    	MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    	tService.makeATransaction(currentUser.getEmail(), email, amount, description, currentUser.getIdUser());
    	return "redirect:/connection?transactionSuccess";
    }
	

}
