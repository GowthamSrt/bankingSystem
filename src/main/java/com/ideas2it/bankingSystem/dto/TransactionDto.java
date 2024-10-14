package com.ideas2it.bankingSystem.dto;

import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private Long id;
    private String transactionId;
    private String description;
    private double amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private Account account;
    private Account receiverAccount;
    private Account senderAccount;

}
