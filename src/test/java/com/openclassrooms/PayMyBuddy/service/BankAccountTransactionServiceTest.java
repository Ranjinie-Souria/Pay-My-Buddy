package com.openclassrooms.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.PayMyBuddy.model.BankAccountTransaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.BankAccountTransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class BankAccountTransactionServiceTest {

	@InjectMocks
	private BankAccountTransactionService bService;
	
	@Mock
    private BankAccountTransactionRepository bRepo;
	
	@Mock
	private ConnectionRepository connectionRepository;

	@Mock
	private UserRepository uRepo;

	@Test 
	void getTransactionsForEmail_shouldReturnAllTransac(){
		List<BankAccountTransaction> transactions = new ArrayList<>();
		User user = new User("toto","toto@email.com","toto","10");
		BankAccountTransaction transaction1 = new BankAccountTransaction("toto","toto@email.com","toto",user);
		transactions.add(transaction1);
		when(uRepo.findByEmail("toto@email.com")).thenReturn(Optional.of(user));
		when(bRepo.findByIdUser(user)).thenReturn(transactions);
			  
		assertEquals(1,bService.getTransactionsForEmail("toto@email.com").size()); 
	}

}
