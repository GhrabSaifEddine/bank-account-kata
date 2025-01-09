package com.project.bankaccount.domain.services.impl;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.mappers.BankAccountMapper;
import com.project.bankaccount.domain.mappers.TransactionMapper;
import com.project.bankaccount.domain.models.BankAccountModel;
import com.project.bankaccount.domain.models.TransactionModel;
import com.project.bankaccount.domain.services.AccountManagerQueryService;
import com.project.bankaccount.infrastructure.BankAccountRepository;
import com.project.bankaccount.infrastructure.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountManagerQueryServiceImpl implements AccountManagerQueryService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired TransactionRepository transactionRepository;

    private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private TransactionMapper transactionMapper = new TransactionMapper();

    @Override
    public BankAccountDto getAccountStatement(String accountId) throws BankAccountException {
        Optional<BankAccountModel> requestedBankAccount =
            bankAccountRepository.findById(accountId);
        if (requestedBankAccount.isEmpty()) {
            throw new BankAccountException("Bank Account identifier is not valid!");
        }
        return bankAccountMapper.convertToDto(requestedBankAccount.get());
    }

    @Override
    public List<TransactionDto> printAccountStatements(String accountId) throws BankAccountException {
        Optional<BankAccountModel> requestedBankAccount =
            bankAccountRepository.findById(accountId);
        if (requestedBankAccount.isEmpty()) {
            throw new BankAccountException("Bank Account identifier is not valid!");
        }

        List<TransactionModel> transactionsByAccountId = transactionRepository.findByBankAccount(requestedBankAccount.get());
        if (transactionsByAccountId.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return transactionsByAccountId.stream().map(model -> transactionMapper.convertToDto(model))
            .collect(Collectors.toList());
    }
}
