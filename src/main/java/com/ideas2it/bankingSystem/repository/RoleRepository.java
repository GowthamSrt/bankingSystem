package com.ideas2it.bankingSystem.repository;

import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);

    boolean existsByRoleType(RoleType roleType);
}
