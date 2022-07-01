package com.openclassrooms.PayMyBuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	
	@Modifying
	@Query(value = "UPDATE user SET `balance` = :newBalance WHERE `email` = :email", nativeQuery = true)
	@Transactional
	void addConnectionForId(@Param("email") String email,@Param("newBalance") String newBalance);	
	

}
