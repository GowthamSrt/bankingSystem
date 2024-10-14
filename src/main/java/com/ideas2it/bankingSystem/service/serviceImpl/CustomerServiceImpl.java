package com.ideas2it.bankingSystem.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.PasswordUpdateDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import com.ideas2it.bankingSystem.dto.UserUpdateDto;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.exception.UnauthorizedException;
import com.ideas2it.bankingSystem.mapper.UserMapper;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.User;
import com.ideas2it.bankingSystem.repository.AccountRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import com.ideas2it.bankingSystem.service.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public double getBalance(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        return account.getBalance();
    }

    @Override
    public UserResponseDto updateUserDetails(String accountNumber, UserUpdateDto userUpdateDto) {
        User user = getUserByAccountNumber(accountNumber);
        user.setName(userUpdateDto.getName());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public void updatePassword(String accountNumber, PasswordUpdateDto passwordUpdateDto) {
        User user = getUserByAccountNumber(accountNumber);
        if (!passwordUpdateDto.getOldPassword().equals(user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials!");
        }
        user.setPassword(passwordUpdateDto.getNewPassword());
        userRepository.save(user);
    }

    private Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
    }

    private User getUserByAccountNumber(String accountNumber) {
        return userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }
}
