package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.TransactionRequestDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.dto.TransferRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    String creditAmount(String accountNumber, TransactionRequestDto transactionRequest);

    String debitAmount(String accountNumber, TransactionRequestDto transactionRequest);

    TransactionResponseDto processTransfer(String accountNumber, TransferRequestDto transferRequestDto);

    List<TransactionResponseDto> getTransactionHistory(String accountNumber);
}
