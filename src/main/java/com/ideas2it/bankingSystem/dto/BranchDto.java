package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchDto {
    private Long id;
    private String name;
    private String location;
    private Long bankId;
}