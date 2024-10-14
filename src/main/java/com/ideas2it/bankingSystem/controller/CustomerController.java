package com.ideas2it.bankingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.bankingSystem.dto.PasswordUpdateDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import com.ideas2it.bankingSystem.dto.UserUpdateDto;
import com.ideas2it.bankingSystem.service.CustomerService;
import com.ideas2it.bankingSystem.service.serviceImpl.BranchServiceImpl;

@RestController
@RequestMapping("banking/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger LOGGER = LogManager.getLogger(BranchServiceImpl.class);


    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam  String accountNumber) {
        double balance = customerService.getBalance(accountNumber);
        LOGGER.info("Available balance in account {} ", accountNumber + "is : " + balance);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<UserResponseDto> updateUserDetails(@RequestParam String accountNumber, @RequestBody UserUpdateDto userUpdateDto) {
        UserResponseDto updatedUser = customerService.updateUserDetails(accountNumber, userUpdateDto);
        LOGGER.info("User details updated successfully!");
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String accountNumber, @RequestBody PasswordUpdateDto passwordUpdateDto) {
        customerService.updatePassword(accountNumber, passwordUpdateDto);
        LOGGER.info("Password updated successfully!");
        return ResponseEntity.ok("Password updated successfully");
    }
}
