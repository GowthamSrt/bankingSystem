package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.UserRegisterDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto registerUser(UserRegisterDto userDto);

}
