package com.ideas2it.bankingSystem.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.bankingSystem.dto.TransactionRequestDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.dto.TransferRequestDto;
import com.ideas2it.bankingSystem.service.TransactionService;

@RestController
@RequestMapping("banking/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/credit")
    public ResponseEntity<String> creditAmount(@RequestParam String accountNumber, @RequestBody TransactionRequestDto transactionRequestDto) {
        String transactionId = transactionService.creditAmount(accountNumber, transactionRequestDto);
        return ResponseEntity.ok("Amount credited successfully, Transaction Id : " + transactionId);
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debitAmount(@RequestParam String accountNumber, @RequestBody TransactionRequestDto transactionRequestDto) {
        String transactionId = transactionService.debitAmount(accountNumber, transactionRequestDto);
        return ResponseEntity.ok("Amount debited successfully, Transaction Id : " + transactionId);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getTransactionHistory(@RequestParam String accountNumber) {
        List<TransactionResponseDto> transactions = transactionService.getTransactionHistory(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDto> transfer(@RequestParam String accountNumber, @RequestBody TransferRequestDto transferRequestDto) {
        TransactionResponseDto transactionResponse = transactionService.processTransfer(accountNumber, transferRequestDto);
        return ResponseEntity.ok(transactionResponse);
    }
}
