package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.BranchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchService {

    BranchDto createBranch(BranchDto branchDto);

    BranchDto updateBranch(Long id, BranchDto branchDto);

    void deleteBranch(Long id);

    List<BranchDto> getAllBranches();

    List<BranchDto> getBranchesByBankAndLocation(Long bankId, String location);

    BranchDto getBranchById(Long id);
}

