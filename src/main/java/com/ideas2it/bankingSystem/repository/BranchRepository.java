package com.ideas2it.bankingSystem.repository;

import java.util.List;

import com.ideas2it.bankingSystem.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByIsDeletedFalse();

    Branch findByIdAndIsDeletedFalse(Long id);

    List<Branch> findByBankIdAndLocationAndIsDeletedFalse(Long bankId, String location);


}
