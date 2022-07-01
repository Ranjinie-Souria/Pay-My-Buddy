package com.openclassrooms.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface ConnectionRepository  extends JpaRepository<User, Integer> {
	
	@Query(value = "INSERT INTO connection(`user_iduser`,`user_iduser1`) values(:idUser,:idFriend)", nativeQuery = true)
	public void addConnectionForId(int idUser, int idFriend);	
	

}