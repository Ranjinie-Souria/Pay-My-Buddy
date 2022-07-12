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

import com.openclassrooms.PayMyBuddy.model.BankAccountTransaction;
import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.BankAccountTransactionService;
import com.openclassrooms.PayMyBuddy.service.CompareBalanceToTransaction;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class BankTransactionController {
	
	@Autowired
	private BankAccountTransactionService bankService;

	@Autowired
	private UserService uService;
	
	@GetMapping("/bankTransaction")
    public String showTransactions(Authentication authentication,ModelMap model) {		
		AddHTML.addFooterHeader(model);
		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
		List<BankAccountTransaction> transactions = bankService.getTransactionsForEmail(currentUser.getEmail());
		model.addAttribute("transaction", transactions);
        return "banktransactions";
    }
	
    @RequestMapping(value = "/bankTransaction", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String addTransaction(@AuthenticationPrincipal MyUserDetails loggedUser,Authentication authentication,@RequestParam String iban,@RequestParam String amount,@RequestParam String description) {
    	try {
    		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    		User user = uService.getUserById(currentUser.getIdUser()).get();
    		
    		if(CompareBalanceToTransaction.hasEnoughMoney(amount, user.getBalance())) {
    			double moneySent = Double.parseDouble(amount);
    			double fee = (moneySent * 0.005);
    			double moneyReceived = moneySent - fee;
    			uService.setSendAmount(user.getIdUser(), amount);
    			BankAccountTransaction transaction = new BankAccountTransaction(iban, String.valueOf(moneyReceived),description, user);
    			bankService.saveBankAccountTransaction(transaction);
    			loggedUser.setBalance(uService.getUserById(currentUser.getIdUser()).get().getBalance());
    			return "redirect:/bankTransaction?transactionSuccess";
    		}
    		
    		return "redirect:/bankTransaction?balanceError";
    		
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		return "redirect:/bankTransaction?transactionError";
    	}
    }
	
    @RequestMapping(value = "/bankTransaction/get", method = RequestMethod.POST
            ,  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public String getBankTransaction(@AuthenticationPrincipal MyUserDetails loggedUser,Authentication authentication,@RequestParam String iban,@RequestParam String amount) {
    	try {
    		MyUserDetails currentUser = CurrentUser.getCurrentUser(authentication);
    		User user = uService.getUserById(currentUser.getIdUser()).get();
    		
    		uService.setGetAmount(user.getIdUser(), amount);
    		
    		BankAccountTransaction transaction = new BankAccountTransaction(iban,amount,user);
    		
    		bankService.saveBankAccountTransaction(transaction);
    		loggedUser.setBalance(uService.getUserById(currentUser.getIdUser()).get().getBalance());
    		return "redirect:/bankTransaction?transactionSuccess";
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		return "redirect:/bankTransaction?transactionError";
    	}
    }

}
