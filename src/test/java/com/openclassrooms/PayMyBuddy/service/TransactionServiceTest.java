package com.openclassrooms.PayMyBuddy.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
class TransactionServiceTest {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	void getTransactionsForEmail() {
    	//List<Transaction> transactions = transactionRepository.findAllBySenderEmail(null);
	}

}
