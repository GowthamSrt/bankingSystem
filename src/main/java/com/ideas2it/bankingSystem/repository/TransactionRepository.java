package com.ideas2it.bankingSystem.repository;

import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountOrSenderAccountOrReceiverAccount(Account account, Account senderAccount, Account receiverAccount);
}
