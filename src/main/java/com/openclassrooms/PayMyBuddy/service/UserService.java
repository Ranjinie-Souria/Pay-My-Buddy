package com.openclassrooms.PayMyBuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);		
	}
	
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}
	
	public List<String> getConnectionsForEmail(String email){
		
		List<String> connectionEmails = new ArrayList<String>();
		for (User element : userRepository.findByEmail(email).get().getConnections()) {
			connectionEmails.add(element.getEmail());
		}
		return connectionEmails;
	}
	
	public void addConnectionForEmail(String email, String friendEmail) {
		if(email != friendEmail) {
			User newFriend = userRepository.findByEmail(friendEmail).get();
			User currentUser = userRepository.findByEmail(email).get();
			if(newFriend != null) {
				connectionRepository.addConnectionForId(currentUser.getIdUser(), newFriend.getIdUser());				
			}
			else {
				System.out.println("This email address is not used by any user.");
			}
		}
		else {
			System.out.println("Email address is the same as the authentificated user.");
		}
	}
	
	public void deleteConnectionForEmail(String email, String friendEmail) {
		if(email != friendEmail) {
			User newFriend = userRepository.findByEmail(friendEmail).get();
			userRepository.findByEmail(email).get().getConnections().remove(newFriend);	
		}
	}

}