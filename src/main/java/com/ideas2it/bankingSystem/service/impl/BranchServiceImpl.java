package com.ideas2it.bankingSystem.service.impl;

import com.ideas2it.bankingSystem.dto.BranchDto;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.mapper.BranchMapper;
import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.model.Branch;
import com.ideas2it.bankingSystem.repository.BankRepository;
import com.ideas2it.bankingSystem.repository.BranchRepository;
import com.ideas2it.bankingSystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;
    private static final Logger LOGGER = LogManager.getLogger(BranchServiceImpl.class);



    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        Bank bank = bankRepository.findByIdAndIsDeletedFalse(branchDto.getBankId());
        Branch branch = BranchMapper.toEntity(branchDto);
        branch.setBank(bank);
        Branch savedBranch = branchRepository.save(branch);
        LOGGER.info("Branch is saved with Id : " + savedBranch.getId());
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto) {
        Branch existingBranch = branchRepository.findByIdAndIsDeletedFalse(id);
        if (null == existingBranch) {
            LOGGER.warn("No Bank found with given Id : " + id);
            throw new ResourceNotFoundException("No Bank found with given Id : " + id);
        }
        existingBranch.setName(branchDto.getName());
        existingBranch.setLocation(branchDto.getLocation());
        Bank bank = bankRepository.findByIdAndIsDeletedFalse(branchDto.getBankId());
        if (null == bank) {
            LOGGER.warn("No Bank found with given Id : " + branchDto.getBankId());
            throw new ResourceNotFoundException("No Bank found with given Id : " + branchDto.getBankId());
        }
        existingBranch.setBank(bank);
        Branch updatedBranch = branchRepository.save(existingBranch);
        LOGGER.info("Branch updated successfully with Id {}", updatedBranch.getId());
        return BranchMapper.toDto(updatedBranch);
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branches = branchRepository.findAllByIsDeletedFalse();
        if (branches.isEmpty()) {
            LOGGER.warn("No Branches were found");
            throw new ResourceNotFoundException("No branches were found");
        }
        return branches.stream()
                .map(BranchMapper :: toDto)
                .toList();
    }

    @Override
    public List<BranchDto> getBranchesByBankAndLocation(Long bankId, String location) {
        List<Branch> branches = branchRepository.findByBankIdAndLocationAndIsDeletedFalse(bankId, location);
        return branches.stream().map(BranchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BranchDto getBranchById(Long id) {
        Branch branch = branchRepository.findByIdAndIsDeletedFalse(id);
        if (null == branch) {
            LOGGER.warn("No Bank found with given Id : " + id);
            throw new ResourceNotFoundException("No Bank found with given Id : " + id);
        }
        return BranchMapper.toDto(branch);
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch = branchRepository.findByIdAndIsDeletedFalse(id);
        if (null == branch) {
            LOGGER.warn("No Bank found with given Id : " + id);
            throw new ResourceNotFoundException("No Bank found with given Id : " + id);
        }
        branch.setDeleted(true);
        branchRepository.save(branch);
        LOGGER.info("Branch deleted successfully with id {}", id);
    }
}

