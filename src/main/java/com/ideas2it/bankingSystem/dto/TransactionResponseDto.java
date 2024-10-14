package com.ideas2it.bankingSystem.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.ideas2it.bankingSystem.model.TransactionType;

@Data
@Builder
public class TransactionResponseDto {
    private String transactionId;
    private Double amount;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime transactionDate;
    private String senderAccountNumber;
    private String receiverAccountNumber;
}
