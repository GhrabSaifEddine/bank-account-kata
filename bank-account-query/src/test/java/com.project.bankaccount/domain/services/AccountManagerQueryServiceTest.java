package com.project.bankaccount.domain.services;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.mappers.BankAccountMapper;
import com.project.bankaccount.domain.models.BankAccountModel;
import com.project.bankaccount.domain.services.impl.AccountManagerQueryServiceImpl;
import com.project.bankaccount.infrastructure.BankAccountRepository;
import com.project.bankaccount.infrastructure.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountManagerQueryServiceTest {
    @InjectMocks
    private AccountManagerQueryService service = new AccountManagerQueryServiceImpl();
    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    private final BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private final BankAccountModel bankAccountModel =
        new BankAccountModel("1", BigDecimal.TEN, "FakeUser", LocalDateTime.now().minusHours(5),
            LocalDateTime.now().minusHours(4));

    @Test
    void shouldGetAccountStatementWithSuccess() throws BankAccountException {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // When
        BankAccountDto selectedAccount =
            service.getAccountStatement(bankAccountModel.getAccountId());

        // Then
        assertEquals(bankAccountMapper.convertToDto(bankAccountModel).toString(), selectedAccount.toString());
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldFailWhenGetAccountStatementDueToInexistantBankAccount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.empty());

        // Then
        assertThrows(BankAccountException.class,
            () -> service.getAccountStatement(bankAccountModel.getAccountId())).getMessage()
            .equals("Bank Account identifier is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }


    @Test
    void shouldPrintAccountStatementsWithSuccess() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertDoesNotThrow(() -> service.printAccountStatements(bankAccountModel.getAccountId()));
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
        verify(transactionRepository).findByBankAccount(bankAccountModel);
    }

    @Test
    void shouldFailWhenPrintAccountStatementsDueToInexistantBankAccount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.empty());

        // Then
        assertThrows(BankAccountException.class,
            () -> service.printAccountStatements(bankAccountModel.getAccountId())).getMessage()
            .equals("Bank Account identifier is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldPrintAccountStatementsWithSuccessForFullAccountTransactions() throws BankAccountException {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        List<TransactionDto> transactions = service.printAccountStatements(bankAccountModel.getAccountId());
        assertEquals(0, transactions.size());
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

}