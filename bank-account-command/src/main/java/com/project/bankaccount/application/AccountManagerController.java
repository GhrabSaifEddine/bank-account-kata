package com.project.bankaccount.application;

import com.project.bankaccount.domain.dto.AmountDto;
import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.services.AccountManagerCmdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagerController.class);

    @Autowired
    AccountManagerCmdService accountManagerCmdService;

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable(value = "accountId") String accountId,
        @RequestBody AmountDto deposit) {
        try {
            accountManagerCmdService.makeDeposit(accountId, deposit.getAmount());
            return new ResponseEntity<>("Deposit amount has been processed successfully!", HttpStatus.OK);
        } catch (BankAccountException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{accountId}/withdrawal")
    public ResponseEntity<String> withdrawal(@PathVariable(value = "accountId") String accountId,
        @RequestBody AmountDto withdrawal) {
        try {
            accountManagerCmdService.makeWithdrawal(accountId, withdrawal.getAmount());
            return new ResponseEntity<>("Withdrawal amount has been processed successfully!", HttpStatus.OK);
        } catch (BankAccountException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
