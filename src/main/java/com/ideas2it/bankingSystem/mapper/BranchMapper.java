package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.BranchDto;
import com.ideas2it.bankingSystem.model.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {
    public static BranchDto toDto(Branch branch) {
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .location(branch.getLocation())
                .bankId(branch.getBank().getId())
                .build();
    }

    public static Branch toEntity(BranchDto branchDto) {
        return Branch.builder()
                .id(branchDto.getId())
                .name(branchDto.getName())
                .location(branchDto.getLocation())
                .build();
    }
}