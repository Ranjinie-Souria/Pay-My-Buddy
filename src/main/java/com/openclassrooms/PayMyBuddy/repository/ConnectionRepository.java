package com.openclassrooms.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface ConnectionRepository  extends JpaRepository<User, Integer> {
	
	@Modifying
	@Query(value = "INSERT INTO connection(`user_iduser`,`user_iduser1`) values(:idUser,:idFriend)", nativeQuery = true)
	@Transactional
	void addConnectionForId(@Param("idUser") int idUser,@Param("idFriend") int idFriend);	
	

}