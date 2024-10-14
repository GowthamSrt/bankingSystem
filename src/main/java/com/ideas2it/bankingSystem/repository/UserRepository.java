package com.ideas2it.bankingSystem.repository;

import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndBankId(String email,Long bankId);

    Optional<User> findByEmailAndBank(String email, Bank bank);

    @Query("SELECT u FROM User u WHERE u.branch.id = :branchId AND u.role.roleType = :roleType")
    Optional<User> findByBranchIdAndRoleType(@Param("branchId") Long branchId, @Param("roleType") RoleType roleType);

    Optional<User> findByEmailAndRole_RoleType(String email, RoleType roleType);

    Optional<User> findByAccountNumber(String accountNumber);

    Optional<User> findUserByEmail(String email);
}
