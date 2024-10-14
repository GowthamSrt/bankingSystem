package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerLoginDto {
    private String accountNumber;
    private String password;
}
