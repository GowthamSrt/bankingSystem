package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferRequestDto {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private String description;
}
