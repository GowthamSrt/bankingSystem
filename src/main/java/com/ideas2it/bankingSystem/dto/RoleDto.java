package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

import com.ideas2it.bankingSystem.model.RoleType;

@Data
@Builder
public class RoleDto {
    private Long id;
    private RoleType roleType;
    private String name;
}
