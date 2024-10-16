package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserRegisterDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String idProofNumber;
    private Long bankId;
    private Long branchId;
    private String roleType;
}
