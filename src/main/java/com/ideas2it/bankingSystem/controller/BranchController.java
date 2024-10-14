package com.ideas2it.bankingSystem.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.bankingSystem.dto.BranchDto;
import com.ideas2it.bankingSystem.service.BranchService;

@RestController
@RequestMapping("banking/api/v1/admin/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        BranchDto createdBranch = branchService.createBranch(branchDto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) {
        BranchDto updatedBranch = branchService.updateBranch(id, branchDto);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        List<BranchDto> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/by-bank/{bankId}?location={location}")
    public ResponseEntity<List<BranchDto>> getBranchesByBankAndLocation(
            @RequestParam Long bankId,
            @RequestParam String location) {
        List<BranchDto> branches = branchService.getBranchesByBankAndLocation(bankId, location);
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) {
        BranchDto branchDto = branchService.getBranchById(id);
        return new ResponseEntity<>(branchDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
