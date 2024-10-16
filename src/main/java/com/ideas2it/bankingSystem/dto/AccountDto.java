package com.ideas2it.bankingSystem.dto;

import lombok.Builder;
import lombok.Data;

import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.model.Branch;
import com.ideas2it.bankingSystem.model.User;

@Data
@Builder
public class AccountDto {

    private String accountNumber;
    private double balance;
    private User user;
    private Branch branch;
    private Bank bank;
}
