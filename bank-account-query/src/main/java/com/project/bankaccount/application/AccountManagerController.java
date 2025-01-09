package com.project.bankaccount.application;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.services.AccountManagerQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagerController.class);

    @Autowired
    AccountManagerQueryService accountManagerQueryService;

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccountDto> getAccountStatement(@PathVariable(value = "accountId") String accountId) {
        try {
            BankAccountDto accountDto = accountManagerQueryService.getAccountStatement(accountId);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (BankAccountException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> printAccountStatements(
        @PathVariable(value = "accountId") String accountId) {
        try {
            List<TransactionDto> accountStatements = accountManagerQueryService.printAccountStatements(accountId);
            return new ResponseEntity<>(accountStatements, HttpStatus.OK);
        } catch (BankAccountException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
