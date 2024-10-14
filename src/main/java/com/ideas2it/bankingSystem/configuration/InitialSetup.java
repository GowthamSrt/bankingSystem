package com.ideas2it.bankingSystem.configuration;

import com.ideas2it.bankingSystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialSetup implements CommandLineRunner {
    private final RoleService roleService;

    public void run(String... args) throws Exception {
        roleService.addRoles();
    }

}