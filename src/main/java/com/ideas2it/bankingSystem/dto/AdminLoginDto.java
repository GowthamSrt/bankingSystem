package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginDto {
    private String email;
    private String password;
}
