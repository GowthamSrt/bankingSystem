package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    void addRoles();

    Role getRoleByName(RoleType roleType);
}
