package com.project.bankaccount.domain.services;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import com.project.bankaccount.domain.exceptions.BankAccountException;

import java.util.List;

public interface AccountManagerQueryService {
    BankAccountDto getAccountStatement(String accountId) throws BankAccountException;
    List<TransactionDto> printAccountStatements(String accountId) throws BankAccountException;
}
