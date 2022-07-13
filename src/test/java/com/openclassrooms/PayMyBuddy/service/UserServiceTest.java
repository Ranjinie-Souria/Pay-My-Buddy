package com.openclassrooms.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@InjectMocks
	private UserService uService;
	
	@Mock
    private UserRepository uRepo;
	
	@Mock
	private ConnectionRepository connectionRepository;

	@Test 
	void getUsers_shouldReturnAllUsers(){
		List<User> listUser = new ArrayList<>();
		User user1 = new User("toto","toto@email.com","toto","10");
		User user2 = new User("tota","tota@email.com","toto","10");
		User user3 = new User("tote","tote@email.com","toto","10");
		listUser.add(user1);
		listUser.add(user2);
		listUser.add(user3);
		when(uRepo.findAll()).thenReturn(listUser);
			  
		assertEquals(listUser,uService.getUsers()); 
	}

	@Test 
	void getUserById_shouldReturnUser(){
		Optional<User> userA = Optional.of(new User("a", "a@email.com","a", "0"));
		when(uRepo.findById(5)).thenReturn(userA);
		assertEquals("a@email.com",uService.getUserById(5).get().getEmail()); 
	}  
	  
	
	@Test
	void getConnectionsForEmail_shouldReturnEmails() {
		//GIVEN
		List<User> listUser = new ArrayList<>();
		User user1 = new User("toto","toto@email.com","toto","10");
		User user2 = new User("tota","tota@email.com","toto","10");
		User user3 = new User("tote","tote@email.com","toto","10");
		listUser.add(user1);
		listUser.add(user2);
		listUser.add(user3);
		User user = new User("tutu", "tutu@gmail.com","tutu","10000");
		user.setConnections(listUser);
		Optional<User> boxUser = Optional.of(user);
		
		when(uRepo.findByEmail("tutu@gmail.com")).thenReturn(boxUser);
		
		//WHEN
		List<User> result = uService.getConnectionsForEmail("tutu@gmail.com");
		
		//THEN
		assertEquals(3, result.size());
	}
	
	@Test
	void getConnectionsForEmail_shouldReturnNullPointerException() {
		
		when(uRepo.findByEmail("tutu@gmail.com")).thenReturn(null);

		assertThrows(NullPointerException.class,() -> uService.getConnectionsForEmail("tutu@gmail.com"));
	}
	
	
    

}
