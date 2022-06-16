package com.openclassrooms.PayMyBuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.PayMyBuddy.model.BankAccountTransaction;
import com.openclassrooms.PayMyBuddy.repository.BankAccountTransactionRepository;

public class BankAccountTransactionService {
	
	@Autowired
	private BankAccountTransactionRepository batransactionRepository;
	
	public Iterable<BankAccountTransaction> getBankAccountTransactions() {
		return batransactionRepository.findAll();
	}
	
	public Optional<BankAccountTransaction> getBankAccountTransactionById(Integer id) {
		return batransactionRepository.findById(id);
	}
	
	
	public BankAccountTransaction saveBankAccountTransaction(BankAccountTransaction baTransaction) {
		return batransactionRepository.save(baTransaction);		
	}
	
	public void deleteBankAccountTransactionById(Integer id) {
		batransactionRepository.deleteById(id);
	}

}
