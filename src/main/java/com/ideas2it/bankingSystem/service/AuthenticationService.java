package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.AuthenticateResponseDto;
import com.ideas2it.bankingSystem.dto.CustomerLoginDto;

public interface AuthenticationService {

    AuthenticateResponseDto authenticateCustomer(CustomerLoginDto customerLoginDto);
}
