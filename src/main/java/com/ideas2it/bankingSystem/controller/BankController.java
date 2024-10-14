package com.ideas2it.bankingSystem.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.bankingSystem.dto.BankDto;
import com.ideas2it.bankingSystem.service.BankService;


@RestController
@RequiredArgsConstructor
@RequestMapping("banking/api/v1/admin/banks")
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<BankDto> createBank(@RequestParam Long id, @RequestBody BankDto bankDto) {
        BankDto createdBank = bankService.addBank(id, bankDto);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BankDto>> getAllBanks() {
        return new ResponseEntity<>(bankService.getAllBanks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDto> getBankById(@PathVariable Long id) {
        return new ResponseEntity<>(bankService.getBankById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDto> updateBank(@PathVariable Long id, @RequestBody BankDto bankDto) {
        return new ResponseEntity<>(bankService.updateBank(id, bankDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankDto> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
