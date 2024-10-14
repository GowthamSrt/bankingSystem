package com.ideas2it.bankingSystem.service;

import com.ideas2it.bankingSystem.dto.BankDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {
    BankDto addBank(Long id, BankDto bankDto);

    List<BankDto> getAllBanks();

    BankDto getBankById(Long id);

    BankDto updateBank(Long id, BankDto bankDto);

    void deleteBank(Long id);
}
