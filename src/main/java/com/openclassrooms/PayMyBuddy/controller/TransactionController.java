package com.openclassrooms.PayMyBuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.CompareBalanceToTransaction;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionService tService;

	@Autowired
	private UserService uService;
	
	@GetMapping("/transaction")
    public String showTransactions(Authentication authentication,ModelMap model) {		
		AddHTML.addFooterHeader(model);
		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
		List<User> connections = uService.getConnectionsForEmail(currentUser.getEmail());		
		model.addAttribute("connection", connections);
		List<Transaction> transactions = tService.getTransactionsForEmail(currentUser.getEmail());
		model.addAttribute("transaction", transactions);
        return "transactions";
    }
	
    @RequestMapping(value = "/transaction", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addTransaction(@AuthenticationPrincipal MyUserDetails loggedUser,Authentication authentication,@RequestParam String email,@RequestParam String amount,@RequestParam String description) {
    	try {
    		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    		int idUser = currentUser.getIdUser();
    		
    		if(CompareBalanceToTransaction.hasEnoughMoney(amount, uService.getUserById(idUser).get().getBalance())) {
    			tService.makeATransaction(currentUser.getEmail(), email, amount, description, idUser);
    			loggedUser.setBalance(uService.getUserById(currentUser.getIdUser()).get().getBalance());
    			return "redirect:/transaction?transactionSuccess";
    		}
    		
    		return "redirect:/transaction?balanceError";
    		
    	}
    	catch(Exception e) {
    		return "redirect:/transaction?transactionError";
    	}
    }
	

}
