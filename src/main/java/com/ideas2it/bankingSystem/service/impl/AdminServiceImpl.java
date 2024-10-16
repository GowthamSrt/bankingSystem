package com.ideas2it.bankingSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.AdminLoginDto;
import com.ideas2it.bankingSystem.dto.AdminResponseDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.exception.UnauthorizedException;
import com.ideas2it.bankingSystem.mapper.AdminMapper;
import com.ideas2it.bankingSystem.mapper.TransactionMapper;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.model.User;
import com.ideas2it.bankingSystem.repository.TransactionRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import com.ideas2it.bankingSystem.service.AdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private static final Logger LOGGER = LogManager.getLogger(AdminService.class);


    public AdminResponseDto adminLogin(AdminLoginDto adminLoginDto) {
        User admin = userRepository.findByEmailAndRole_RoleType(adminLoginDto.getEmail(), RoleType.ADMIN)
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
        if (!admin.getPassword().equals(adminLoginDto.getPassword())) {
            LOGGER.warn("Password mismatch!");
            throw new UnauthorizedException("Invalid email or password");
        }
        LOGGER.info("Admin logged in successfully!");
        return AdminMapper.toDto(admin);
    }

    public List<TransactionResponseDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper :: toResponseDto)
                .toList();
    }
}
