package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.AdminLoginDto;
import com.ideas2it.bankingSystem.dto.AdminResponseDto;
import com.ideas2it.bankingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public static AdminResponseDto toDto(User user) {
        return AdminResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .branch(user.getAccount().getBranch().getName())
                .role(user.getRole().getRoleType().name())
                .build();
    }

    public static User toEntity(AdminLoginDto adminLoginDto) {
        return User.builder()
                .email(adminLoginDto.getEmail())
                .password(adminLoginDto.getPassword())
                .build();
    }
}
