package com.ideas2it.bankingSystem.service.serviceImpl;

import java.util.List;

import com.ideas2it.bankingSystem.exception.UnauthorizedException;
import com.ideas2it.bankingSystem.model.RoleType;
import com.ideas2it.bankingSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.BankDto;
import com.ideas2it.bankingSystem.exception.AlreadyExistsException;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.mapper.BankMapper;
import com.ideas2it.bankingSystem.repository.BankRepository;
import com.ideas2it.bankingSystem.service.BankService;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(BankServiceImpl.class);

    @Override
    public BankDto addBank(Long id,BankDto bankDto) {
        if (bankRepository.existsByNameAndIsDeletedFalse(bankDto.getName())) {
            LOGGER.warn("Bank Already exists", bankDto.getName());
            throw new AlreadyExistsException("Bank Already exists " + bankDto.getName());
        }
        val bank = BankMapper.toEntity(bankDto);
        bankRepository.save(bank);
        LOGGER.info("Bank created with Id : " + bank.getId());
        return BankMapper.toDto(bank);
    }

    @Override
    public List<BankDto> getAllBanks() {
        val bankDtos = bankRepository.findByIsDeletedFalse()
                .stream()
                .map(BankMapper :: toDto)
                .toList();
        if (bankDtos.isEmpty()) {
            LOGGER.warn("No Banks are available");
        }
        LOGGER.info("All available Banks");
        return bankDtos;
    }

    @Override
    public BankDto getBankById(Long id) {
        val bank = bankRepository.findByIdAndIsDeletedFalse(id);
        if (null == bank) {
            LOGGER.warn("No Bank with given Id found", id);
            throw new ResourceNotFoundException("No Bank found : " + id);
        }
        LOGGER.info("Available bank with given Id " + id + " is " + bank.getName());
        return BankMapper.toDto(bank);
    }

    @Override
    public BankDto updateBank(Long id, BankDto bankDto) {
        var bank = bankRepository.findByIdAndIsDeletedFalse(id);
        if (null == bank) {
            LOGGER.warn("No Bank available with given Id");
            throw new ResourceNotFoundException("No Bank with id " + id + " is found");
        }
        bank.setName(bank.getName());
        return BankMapper.toDto(bankRepository.save(bank));
    }

    @Override
    public void deleteBank(Long id) {
        var bank = bankRepository.findByIdAndIsDeletedFalse(id);
        if (null == bank) {
            LOGGER.warn("No Bank available with given Id");
            throw new ResourceNotFoundException("No Bank with id " + id + " is found");
        }
        bank.setDeleted(true);
        bankRepository.save(bank);
        LOGGER.info("Bank with Id " + id + " is deleted successfully");
    }

    private void checkAdmin(String email) {
        val user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        if (!user.getRole().getRoleType().equals(RoleType.ADMIN) || !"admin@example.com".equals(email)) {
            throw new UnauthorizedException("Access Denied! Only the system admin can perform this operation.");
        }
    }
}
