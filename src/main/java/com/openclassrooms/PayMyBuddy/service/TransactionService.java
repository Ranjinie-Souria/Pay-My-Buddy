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
		System.out.print(transaction.getSenderEmail()+transaction.getReceiverEmail()+transaction.getAmount()+transaction.getDescription()+transaction.getIdTransaction());

		return transactionRepository.save(transaction);		
	}
	
	public void deleteTransactionById(Integer id) {
		transactionRepository.deleteById(id);
	}
	
	public void makeATransaction(String senderEmail,String receiverEmail,String amount,String description,int idUser) {
		
		User friend = userService.getUserByEmail(receiverEmail).get();
		
		double moneySent = Integer.parseInt(amount);
		double fee = (moneySent * 0.005);
		double moneyReceived = moneySent - fee;
		
			userService.setSendAmount(idUser, amount);
			int admin = userService.getUserByEmail("admin@admin.com").get().getIdUser();
			userService.setGetAmount(friend.getIdUser(), String.valueOf(moneyReceived));
			userService.setGetAmount(admin, String.valueOf(fee));
			Transaction transaction = new Transaction(senderEmail, receiverEmail, amount, description, friend);
			transactionRepository.save(transaction);
	}
	
	public List<Transaction> getTransactionsForEmail(String email) {
		List<Transaction> listTransac = transactionRepository.findBySenderEmailOrReceiverEmail(email, email);
		return listTransac;
	}
    


}