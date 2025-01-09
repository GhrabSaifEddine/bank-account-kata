package com.project.bankaccount.domain.services;

import com.project.bankaccount.domain.exceptions.BankAccountException;

import java.math.BigDecimal;

public interface AccountManagerCmdService {

    void makeDeposit( String accountId, BigDecimal depositAmount) throws BankAccountException;

    void makeWithdrawal( String accountId, BigDecimal withdrawalAmount) throws BankAccountException;
}
