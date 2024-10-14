package com.ideas2it.bankingSystem.controller;

import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.repository.TransactionRepository;
import com.ideas2it.bankingSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("banking/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> transactions = adminService.getAllTransactions();
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transaction found!");
        }
        return ResponseEntity.ok(transactions);
    }
}
