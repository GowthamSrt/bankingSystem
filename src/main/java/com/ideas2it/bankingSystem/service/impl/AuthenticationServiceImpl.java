package com.ideas2it.bankingSystem.service.impl;

import java.util.Optional;

import com.ideas2it.bankingSystem.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.AuthenticateResponseDto;
import com.ideas2it.bankingSystem.dto.CustomerLoginDto;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.User;
import com.ideas2it.bankingSystem.repository.AccountRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import com.ideas2it.bankingSystem.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LogManager.getLogger(BranchServiceImpl.class);

    @Override
    public AuthenticateResponseDto authenticateCustomer(CustomerLoginDto customerLoginDto) {
        Optional<User> currentUser = userRepository.findByAccountNumber(customerLoginDto.getAccountNumber());
        if (currentUser.isEmpty() || !customerLoginDto.getPassword().equals(currentUser.get().getPassword())) {
            LOGGER.warn("Invalid credentials provided");
            throw new ResourceNotFoundException("Invalid credentials!");
        }
        User user = currentUser.get();
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(user.getAccountNumber());
        if (accountOpt.isEmpty()) {
            LOGGER.warn("Account not found with given account number");
            throw new ResourceNotFoundException("Account not found!");
        }
        return AuthenticationMapper.toDto(user);
    }
}

