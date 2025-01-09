package com.project.bankaccount.domain.services.impl;

import com.project.bankaccount.domain.dto.TransactionType;
import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.models.BankAccountModel;
import com.project.bankaccount.domain.models.TransactionModel;
import com.project.bankaccount.domain.services.AccountManagerCmdService;
import com.project.bankaccount.infrastructure.BankAccountRepository;
import com.project.bankaccount.infrastructure.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountManagerCmdServiceImpl implements AccountManagerCmdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagerCmdServiceImpl.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void makeDeposit(String accountId, BigDecimal depositAmount) throws BankAccountException {
        Optional<BankAccountModel> requestedBankAccount = bankAccountRepository.findById(accountId);
        if (requestedBankAccount.isEmpty()) {
            throw new BankAccountException("Bank Account identifier is not valid!");
        }
        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankAccountException("Deposit amount is not valid!");
        }

        BankAccountModel selectedBankAccount = requestedBankAccount.get();
        selectedBankAccount.setBalance(selectedBankAccount.getBalance().add(depositAmount));
        selectedBankAccount.setLastUpdateDate(LocalDateTime.now());
        bankAccountRepository.save(selectedBankAccount);

        TransactionModel depositTransaction = new TransactionModel(UUID.randomUUID().toString(),
            TransactionType.DEPOSIT, depositAmount, LocalDateTime.now(), selectedBankAccount);
        transactionRepository.save(depositTransaction);
    }

    public void makeWithdrawal(String accountId, BigDecimal withdrawalAmount) throws BankAccountException {
        Optional<BankAccountModel> requestedBankAccount = bankAccountRepository.findById(accountId);
        if (requestedBankAccount.isEmpty()) {
            throw new BankAccountException("Bank Account identifier is not valid!");
        }
        if (withdrawalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankAccountException("Withdrawal amount is not valid!");
        }
        BankAccountModel selectedBankAccount = requestedBankAccount.get();
        if (selectedBankAccount.getBalance().compareTo(withdrawalAmount) < 0) {
            throw new BankAccountException("Withdrawal exceeds account amount!");
        }

        selectedBankAccount.setBalance(selectedBankAccount.getBalance().subtract(withdrawalAmount));
        selectedBankAccount.setLastUpdateDate(LocalDateTime.now());
        bankAccountRepository.save(selectedBankAccount);

        TransactionModel depositTransaction = new TransactionModel(UUID.randomUUID().toString(),
            TransactionType.WITHDRAWAL, withdrawalAmount, LocalDateTime.now(), selectedBankAccount);
        transactionRepository.save(depositTransaction);
    }
}
