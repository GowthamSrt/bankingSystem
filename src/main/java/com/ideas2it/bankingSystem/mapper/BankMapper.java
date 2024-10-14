package com.ideas2it.bankingSystem.mapper;

import org.springframework.stereotype.Component;

import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.dto.BankDto;

@Component
public class BankMapper {
    public static BankDto toDto(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();
    }

    public static Bank toEntity(BankDto bankDto) {
        return Bank.builder()
                .id(bankDto.getId())
                .name(bankDto.getName())
                .build();
    }
}
