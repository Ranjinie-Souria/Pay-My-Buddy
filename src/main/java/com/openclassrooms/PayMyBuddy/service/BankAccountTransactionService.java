package com.openclassrooms.PayMyBuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.BankAccountTransaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.BankAccountTransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class BankAccountTransactionService {
	
	@Autowired
	private BankAccountTransactionRepository batransactionRepository;
	
	@Autowired
	private UserRepository uRepository;
	
	public Iterable<BankAccountTransaction> getBankAccountTransactions() {
		return batransactionRepository.findAll();
	}
	
	public Optional<BankAccountTransaction> getBankAccountTransactionById(Integer id) {
		return batransactionRepository.findById(id);
	}
	
	public List<BankAccountTransaction> getTransactionsForEmail(String email) {
		User userId = uRepository.findByEmail(email).get();
		List<BankAccountTransaction> listTransac = batransactionRepository.findByIdUser(userId);
		return listTransac;
	}
	
	
	public BankAccountTransaction saveBankAccountTransaction(BankAccountTransaction baTransaction) {
		return batransactionRepository.save(baTransaction);		
	}
	
	public void deleteBankAccountTransactionById(Integer id) {
		batransactionRepository.deleteById(id);
	}
	

}
