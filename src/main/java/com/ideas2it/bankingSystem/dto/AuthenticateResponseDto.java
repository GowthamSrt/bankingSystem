package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateResponseDto {
    private String bankName;
    private String branchName;
    private String accountHolderName;
    private String accountNumber;
    private Double balance;
    private String phoneNumber;
}
