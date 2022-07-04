package com.openclassrooms.PayMyBuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.PayMyBuddy.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
		
	@Modifying
	@Query(value = "INSERT INTO transaction(`senderEmail`,`receiverEmail`,`amount`,`description`,`iduser`) "
			+ "values(:senderEmail, :receiverEmail, :amount, :description, :idUser)", nativeQuery = true)
	@Transactional
	void saveTransaction(@Param("senderEmail") String senderEmail,@Param("receiverEmail") String receiverEmail,
						@Param("amount") String amount,@Param("description") String description,
						@Param("idUser") int idUser);


	public List<Transaction> findBySenderEmail(String email);
	
	

}
