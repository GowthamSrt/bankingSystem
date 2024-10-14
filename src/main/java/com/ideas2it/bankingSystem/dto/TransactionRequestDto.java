package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {

    private Double amount;
    private String description;
}
