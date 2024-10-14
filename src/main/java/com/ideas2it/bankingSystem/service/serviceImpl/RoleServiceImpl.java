package com.ideas2it.bankingSystem.service.serviceImpl;

import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.repository.RoleRepository;
import com.ideas2it.bankingSystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public void addRoles() {
        List<Role> roles = new ArrayList<>();
        if(!roleRepository.existsByRoleType(RoleType.ADMIN)) {
            roles.add(Role.builder().roleType(RoleType.ADMIN).build());
            roles.add(Role.builder().roleType(RoleType.CUSTOMER).build());
            roleRepository.saveAll(roles);
        }
    }

    public Role getRoleByName(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }
}
