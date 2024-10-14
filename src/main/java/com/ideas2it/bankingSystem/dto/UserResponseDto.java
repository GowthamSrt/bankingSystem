package com.ideas2it.bankingSystem.dto;

import com.ideas2it.bankingSystem.model.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String name;
    private String email;
    private String accountNumber;
    private RoleType roleType;
}
