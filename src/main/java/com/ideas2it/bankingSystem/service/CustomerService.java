package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface CustomerService {

    double getBalance(String accountNumber);
    UserResponseDto updateUserDetails(String accountNumber, UserUpdateDto updateDto);
    void updatePassword(String accountNumber, PasswordUpdateDto passwordUpdateDto);
}
