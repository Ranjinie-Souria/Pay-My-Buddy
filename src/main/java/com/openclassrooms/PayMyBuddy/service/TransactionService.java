package com.openclassrooms.PayMyBuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserService userService;
	
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}

	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);		
	}
	
	public void deleteTransactionById(Integer id) {
		transactionRepository.deleteById(id);
	}
	
	public void makeATransaction(String senderEmail,String receiverEmail,String amount,String description,int idUser) {
		User friend = userService.getUserByEmail(receiverEmail).get();
		userService.sendAmount(idUser, amount);
		
		double moneySent = Integer.parseInt(amount);
		double fee = (moneySent * 0.005);
		double moneyreceived = moneySent - fee;
		
		userService.getAmount(friend.getIdUser(), String.valueOf(moneyreceived));
		//userService.getAmount(ID ADMIN, String.valueOf(fee));
		
		transactionRepository.saveTransaction(senderEmail,receiverEmail,amount,description,idUser);
	}
	
	public List<Transaction> getTransactionsForEmail(String email) {
		return transactionRepository.findBySenderEmail(email);
	}
    


}