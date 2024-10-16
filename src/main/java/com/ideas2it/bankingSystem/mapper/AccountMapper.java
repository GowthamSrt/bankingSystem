package com.ideas2it.bankingSystem.mapper;

import com.ideas2it.bankingSystem.dto.AccountDto;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.model.Branch;
import com.ideas2it.bankingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .user(account.getUser())
                .bank(account.getBank())
                .branch(account.getBranch())
                .build();
    }

    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .accountNumber(accountDto.getAccountNumber())
                .balance(accountDto.getBalance())
                .user(accountDto.getUser())
                .bank(accountDto.getBank())
                .branch(accountDto.getBranch())
                .build();
    }

    public static Account createAccount(User user, Bank bank, Branch branch) {
        return Account.builder()
                .accountNumber(user.getAccountNumber())
                .balance(0.0)
                .user(user)
                .branch(branch)
                .bank(bank)
                .build();
    }

}
