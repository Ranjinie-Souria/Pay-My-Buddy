package com.openclassrooms.PayMyBuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.PayMyBuddy.model.BankAccountTransaction;
import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Integer> {

	public List<BankAccountTransaction> findByIdUser(User userId);

}
