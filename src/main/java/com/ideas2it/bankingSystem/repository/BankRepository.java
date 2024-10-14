package com.ideas2it.bankingSystem.repository;

import java.util.List;

import com.ideas2it.bankingSystem.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    boolean existsByNameAndIsDeletedFalse(String name);

    List<Bank> findByIsDeletedFalse();

    Bank findByIdAndIsDeletedFalse(long id);
}
