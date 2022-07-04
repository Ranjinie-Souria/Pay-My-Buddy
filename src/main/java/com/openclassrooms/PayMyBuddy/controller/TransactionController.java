package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionService tService;
	
	@Autowired
	private UserService uService;
	
    @RequestMapping(value = "/transaction", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addTransaction(@AuthenticationPrincipal MyUserDetails loggedUser,Authentication authentication,@RequestParam String email,@RequestParam String amount,@RequestParam String description) {
    	MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    	tService.makeATransaction(currentUser.getEmail(), email, amount, description, currentUser.getIdUser());
    	loggedUser.setBalance(uService.getUserById(currentUser.getIdUser()).get().getBalance());
    	return "redirect:/connection?transactionSuccess";
    }
	

}
