package com.ideas2it.bankingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.bankingSystem.dto.AdminLoginDto;
import com.ideas2it.bankingSystem.dto.AdminResponseDto;
import com.ideas2it.bankingSystem.dto.AuthenticateResponseDto;
import com.ideas2it.bankingSystem.dto.CustomerLoginDto;
import com.ideas2it.bankingSystem.service.AdminService;
import com.ideas2it.bankingSystem.service.AuthenticationService;

@RestController
@RequestMapping("banking/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final AdminService adminService;
    private final AuthenticationService authenticationService;


    @PostMapping("/customers")
    public ResponseEntity<AuthenticateResponseDto> customerLogin(@RequestBody CustomerLoginDto customerLoginDto) {
        AuthenticateResponseDto response = authenticationService.authenticateCustomer(customerLoginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin")
    public ResponseEntity<AdminResponseDto> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        AdminResponseDto response = adminService.adminLogin(adminLoginDto);
        return ResponseEntity.ok(response);
    }
}
