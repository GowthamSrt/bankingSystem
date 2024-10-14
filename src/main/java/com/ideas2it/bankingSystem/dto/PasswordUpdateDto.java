package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordUpdateDto {
    private String oldPassword;
    private String newPassword;
}
