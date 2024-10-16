package com.ideas2it.bankingSystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.ideas2it.bankingSystem.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ideas2it.bankingSystem.dto.TransactionRequestDto;
import com.ideas2it.bankingSystem.dto.TransactionResponseDto;
import com.ideas2it.bankingSystem.dto.TransferRequestDto;
import com.ideas2it.bankingSystem.exception.InsufficientFundsException;
import com.ideas2it.bankingSystem.exception.ResourceNotFoundException;
import com.ideas2it.bankingSystem.model.Account;
import com.ideas2it.bankingSystem.model.Transaction;
import com.ideas2it.bankingSystem.model.TransactionType;
import com.ideas2it.bankingSystem.repository.AccountRepository;
import com.ideas2it.bankingSystem.repository.TransactionRepository;
import com.ideas2it.bankingSystem.repository.UserRepository;
import com.ideas2it.bankingSystem.service.TransactionService;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public String creditAmount(String accountNumber, TransactionRequestDto transactionRequest) {
        Account account = getAccountByNumber(accountNumber, "Account not found");
        account.setBalance(account.getBalance() + transactionRequest.getAmount());
        accountRepository.save(account);
        Transaction transaction = saveTransaction(account, transactionRequest, TransactionType.CREDIT, null);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }

    @Override
    public String debitAmount(String accountNumber, TransactionRequestDto transactionRequest) {
        Account account = getAccountByNumber(accountNumber, "Account not found");
        checkSufficientBalance(account, transactionRequest.getAmount());
        account.setBalance(account.getBalance() - transactionRequest.getAmount());
        accountRepository.save(account);
        Transaction transaction = saveTransaction(account, transactionRequest, TransactionType.DEBIT, null);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }

    @Override
    public TransactionResponseDto processTransfer(String accountNumber, TransferRequestDto transferRequestDto) {
        Account senderAccount = getAccountByNumber(accountNumber, "Sender account not found");
        Account receiverAccount = getAccountByNumber(transferRequestDto.getReceiverAccountNumber(), "Receiver account not found");
        checkSufficientBalance(senderAccount, transferRequestDto.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - transferRequestDto.getAmount());
        accountRepository.save(senderAccount);
        receiverAccount.setBalance(receiverAccount.getBalance() + transferRequestDto.getAmount());
        accountRepository.save(receiverAccount);
        Transaction transaction = createTransferTransaction(senderAccount, receiverAccount, transferRequestDto);
        return TransactionMapper.toResponseDto(transaction);
    }

    @Override
    public List<TransactionResponseDto> getTransactionHistory(String accountNumber) {
        Account account = getAccountByNumber(accountNumber, "Account not found");
        List<Transaction> transactions = transactionRepository.findByAccountOrSenderAccountOrReceiverAccount(account, account, account);
        return transactions.stream()
                .map(TransactionMapper :: toResponseDto)
                .toList();
    }

    private Transaction saveTransaction(Account account, TransactionRequestDto request, TransactionType type, Account otherAccount) {
        String transactionId = generateTransactionId();
        return transactionRepository.save(Transaction.builder()
                .transactionId(transactionId)
                .amount(request.getAmount())
                .transactionType(type)
                .description(request.getDescription())
                .account(account)
                .receiverAccount(type == TransactionType.CREDIT ? account : otherAccount)
                .senderAccount(type == TransactionType.DEBIT ? account : otherAccount)
                .transactionDate(LocalDateTime.now())
                .build());
    }

    private Transaction createTransferTransaction(Account senderAccount, Account receiverAccount, TransferRequestDto transferRequestDto) {
        Transaction transaction = Transaction.builder()
                .transactionId(generateTransactionId())
                .description(transferRequestDto.getDescription())
                .amount(transferRequestDto.getAmount())
                .transactionType(TransactionType.TRANSFER)
                .transactionDate(LocalDateTime.now())
                .account(receiverAccount)
                .senderAccount(senderAccount)
                .receiverAccount(receiverAccount)
                .build();
        return transactionRepository.save(transaction);
    }

    private Account getAccountByNumber(String accountNumber, String message) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException(message));
    }

    private void checkSufficientBalance(Account senderAccount, double amount) {
        if (senderAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance for the transaction.");
        }
    }

    private String generateTransactionId() {
        Random random = new Random();
        return String.valueOf(1000000000000000L + random.nextLong(9000000000000000L));
    }
}
