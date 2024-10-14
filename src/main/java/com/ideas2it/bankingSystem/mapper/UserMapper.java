package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.UserRegisterDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import com.ideas2it.bankingSystem.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserRegisterDto toDto(User user) {
        return UserRegisterDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .idProofNumber(user.getIdProofNumber())
                .password(user.getPassword())
                .bankId(user.getBank().getId())
                .branchId(user.getBranch().getId())
                .build();
    }

    public static UserResponseDto toDTO(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .accountNumber(user.getAccountNumber())
                .roleType(user.getRole().getRoleType())
                .build();
    }

    public static User toEntity(UserRegisterDto userRegisterDto) {
        return User.builder()
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .password(userRegisterDto.getPassword())
                .idProofNumber(userRegisterDto.getIdProofNumber())
                .bank(Bank.builder().id(userRegisterDto.getBankId()).build())
                .branch(Branch.builder().id(userRegisterDto.getBranchId()).build())
                .build();
    }
}
