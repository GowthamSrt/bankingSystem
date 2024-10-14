package com.ideas2it.bankingSystem.service.serviceImpl;

import java.util.Optional;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.UserRegisterDto;
import com.ideas2it.bankingSystem.dto.UserResponseDto;
import com.ideas2it.bankingSystem.exception.AlreadyExistsException;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.mapper.UserMapper;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.Bank;
import com.ideas2it.bankingSystem.model.Branch;
import com.ideas2it.bankingSystem.model.Role;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.model.User;
import com.ideas2it.bankingSystem.repository.AccountRepository;
import com.ideas2it.bankingSystem.repository.BankRepository;
import com.ideas2it.bankingSystem.repository.BranchRepository;
import com.ideas2it.bankingSystem.repository.RoleRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import com.ideas2it.bankingSystem.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    public UserResponseDto registerUser(UserRegisterDto userDto) {
        Bank bank = bankRepository.findById(userDto.getBankId())
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found"));
        Branch branch = branchRepository.findById(userDto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        validateBranchBelongsToBank(branch, bank);
        checkIfUserExists(userDto.getEmail(), bank);
        RoleType roleType = RoleType.valueOf(userDto.getRoleType());
        if (roleType.equals(RoleType.CUSTOMER)) {
            checkIfCustomerHasAccountInBank(userDto.getEmail(), bank);
        }
        if (roleType.equals(RoleType.ADMIN)) {
            checkIfBranchAlreadyHasAdmin(branch);
            checkIfUserIsAdminInAnotherBank(userDto.getEmail(), bank);
        }
        User user = createUser(userDto, bank, branch);
        Account account = createAccount(user, bank, branch);
        accountRepository.save(account);
        user.setAccount(account);
        userRepository.save(user);
        return returnUserResponseDto(user);
    }

    private User createUser(UserRegisterDto userDto, Bank bank, Branch branch) {
        User user = UserMapper.toEntity(userDto);
        user.setBank(bank);
        user.setBranch(branch);
        user.setAccountNumber(generateAccountNumber());
        Role role = roleRepository.findByRoleType(RoleType.valueOf(userDto.getRoleType()));
        user.setRole(role);
        return userRepository.save(user);
    }

    private void checkIfBranchAlreadyHasAdmin(Branch branch) {
        Optional<User> existingAdmin = userRepository.findByBranchIdAndRoleType(branch.getId(), RoleType.ADMIN);
        if (existingAdmin.isPresent()) {
            throw new AlreadyExistsException("This branch already has an admin.");
        }
    }

    private void checkIfUserIsAdminInAnotherBank(String email, Bank currentBank) {
        Optional<User> existingAdmin = userRepository.findByEmailAndRole_RoleType(email, RoleType.ADMIN);
        if (existingAdmin.isPresent() && !existingAdmin.get().getBank().getId().equals(currentBank.getId())) {
            throw new AlreadyExistsException("The user is already an admin of another bank");
        }
    }

    private void checkIfCustomerHasAccountInBank(String email, Bank bank) {
        Optional<User> existingUser = userRepository.findByEmailAndBankId(email, bank.getId());
        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("Customer already has an account in this bank.");
        }
    }

    private void validateBranchBelongsToBank(Branch branch, Bank bank) {
        if (!branch.getBank().getId().equals(bank.getId())) {
            throw new IllegalArgumentException("Branch does not belong to the specified bank.");
        }
    }

    private void checkIfUserExists(String email, Bank bank) {
        Optional<User> existingUser = userRepository.findByEmailAndBank(email, bank);
        if (existingUser.isPresent()) {
            LOGGER.warn("User already exists");
            throw new AlreadyExistsException("User already exists.");
        }
    }

    private Account createAccount(User user, Bank bank, Branch branch) {
        Account account = Account.builder()
                .accountNumber(user.getAccountNumber())
                .balance(0.0)
                .user(user)
                .branch(branch)
                .bank(bank)
                .build();
        accountRepository.save(account);
        user.setAccount(account);
        userRepository.save(user);
        return account;
    }

    private UserResponseDto returnUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .accountNumber(user.getAccountNumber())
                .roleType(user.getRole().getRoleType())
                .build();
    }

    private String generateAccountNumber() {
        Random random = new Random();
        return String.valueOf(100000000000L + random.nextLong(900000000000L));
    }
}
