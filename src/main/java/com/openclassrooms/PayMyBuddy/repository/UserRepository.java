package com.openclassrooms.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Iterable<User> findByEmail(String name);

	public Iterable<User> findByUsername(String name);
	
	

}
