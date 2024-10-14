package com.ideas2it.bankingSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.bankingSystem.dto.UserRegisterDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import com.ideas2it.bankingSystem.service.UserService;

@RestController
@RequestMapping("banking/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        UserResponseDto newUser = userService.registerUser(userRegisterDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}

