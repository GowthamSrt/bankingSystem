package com.ideas2it.bankingSystem.configuration;

import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.model.User;
import com.ideas2it.bankingSystem.repository.RoleRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (!userRepository.findUserByEmail("admin@gmail.com").isPresent()) {
                Role adminRole = roleRepository.findByRoleType(RoleType.ADMIN);
                User admin = new User();
                admin.setEmail("admin@gmail.com");
                admin.setPassword("admin@123");
                admin.setRole(adminRole);
                admin.setName("System Admin");
                userRepository.save(admin);
                System.out.println("Admin user created: admin@gmail.com");
            } else {
                System.out.println("Admin exists!");
            }

        };
    }
}

