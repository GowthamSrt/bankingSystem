package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.AuthenticateResponseDto;
import com.ideas2it.bankingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public static AuthenticateResponseDto toDto(User user) {
        return AuthenticateResponseDto.builder()
                .bankName(user.getBank().getName())
                .branchName(user.getBranch().getName())
                .accountHolderName(user.getName())
                .accountNumber(user.getAccountNumber())
                .balance(user.getAccount().getBalance())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
