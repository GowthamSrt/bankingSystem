package com.ideas2it.bankingSystem.mapper;

import org.springframework.stereotype.Component;

import com.ideas2it.bankingSystem.dto.TransactionDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.model.Transaction;

@Component
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

    public static Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .transactionId(transactionDto.getTransactionId())
                .description(transactionDto.getDescription())
                .amount(transactionDto.getAmount())
                .account(transactionDto.getAccount())
                .transactionDate(transactionDto.getTransactionDate())
                .transactionType(transactionDto.getTransactionType())
                .senderAccount(transactionDto.getSenderAccount())
                .receiverAccount(transactionDto.getReceiverAccount())
                .build();
    }
}
