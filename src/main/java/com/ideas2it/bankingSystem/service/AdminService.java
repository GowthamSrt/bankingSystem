package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.AdminLoginDto;
import com.ideas2it.bankingSystem.dto.AdminResponseDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    AdminResponseDto adminLogin(AdminLoginDto adminLoginDto);

    List<TransactionResponseDto> getAllTransactions();
}
