package com.openclassrooms.PayMyBuddy.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

import ch.qos.logback.core.spi.LifeCycle;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
	
	@Mock
	private UserService uService;
	@InjectMocks
	private TransactionService tService;
	
	@Mock
    private UserRepository uRepo;
	@Mock
    private TransactionRepository tRepo;
	@Mock
	private ConnectionRepository cRepo;
	 
    
	@Test
	void makeATransaction_shouldCreateNewTransac() {
		User admin = new User("admin", "admin@admin.com", "a","0"); 
		User userA = new User("a", "a@a.com", "a","0"); 
		User userB = new User("b", "b@b.com", "b", "0");
		admin.setIdUser(0);
		userA.setIdUser(1);
		userB.setIdUser(2);
		List<User> connections = new ArrayList<User>();
		connections.add(userB);
		userA.setConnections(connections);
		Transaction transac = new Transaction("b@b.com", "a@a.com", "10", "First Transaction", userA);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transac);
		when(tRepo.findBySenderEmailOrReceiverEmail("b@b.com", "b@b.com")).thenReturn(transactions);
		
		when(uService.getUserByEmail("b@b.com")).thenReturn(Optional.of(userB));
		when(uService.getUserByEmail("admin@admin.com")).thenReturn(Optional.of(admin));
		tService.makeATransaction("a@a.com", "b@b.com", "10","First Transaction", 1);
    	List<Transaction> t = tService.getTransactionsForEmail("b@b.com");
    	Assertions.assertEquals(1,t.size());
	}

}
