package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponseDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String branch;
    private String role;
}
