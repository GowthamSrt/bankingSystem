package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.TransactionRequestDto;
import com.ideas2it.bankingSystem.dto.TransferRequestDto;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.TransactionType;
import com.ideas2it.bankingSystem.repository.TransactionRepository;
import com.ideas2it.bankingSystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.ideas2it.bankingSystem.dto.TransactionDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.model.Transaction;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    public static TransactionResponseDto toResponseDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .transactionDate(transaction.getTransactionDate())
                .transactionType(transaction.getTransactionType())
                .senderAccountNumber(transaction.getSenderAccount().getAccountNumber())
                .receiverAccountNumber(transaction.getReceiverAccount().getAccountNumber())
                .build();
    }

    public static Transaction createTransfer (Account senderAccount, Account receiverAccount, TransferRequestDto transferRequestDto, String transactionId) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setAmount(transferRequestDto.getAmount());
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setDescription(transferRequestDto.getDescription());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccount(receiverAccount);
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        return transaction;
    }

    public static Transaction saveTransaction(Account account, TransactionRequestDto request, TransactionType type, Account otherAccount, String transactionId) {
        return Transaction.builder()
                .transactionId(transactionId)
                .amount(request.getAmount())
                .transactionType(type)
                .description(request.getDescription())
                .account(account)
                .receiverAccount(type == TransactionType.CREDIT ? account : otherAccount)
                .senderAccount(type == TransactionType.DEBIT ? account : otherAccount)
                .transactionDate(LocalDateTime.now())
                .build();
    }
}
