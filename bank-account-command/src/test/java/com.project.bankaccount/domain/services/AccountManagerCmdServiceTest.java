package com.project.bankaccount.domain.services;

import com.project.bankaccount.domain.exceptions.BankAccountException;
import com.project.bankaccount.domain.models.BankAccountModel;
import com.project.bankaccount.domain.services.impl.AccountManagerCmdServiceImpl;
import com.project.bankaccount.infrastructure.BankAccountRepository;
import com.project.bankaccount.infrastructure.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountManagerCmdServiceTest {
    @InjectMocks
    private AccountManagerCmdService service = new AccountManagerCmdServiceImpl();
    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    private final BankAccountModel bankAccountModel =
        new BankAccountModel("1", BigDecimal.TEN, "FakeUser", LocalDateTime.now().minusHours(5),
            LocalDateTime.now().minusHours(4));

    @Test
    void shouldMakeDepositWithSuccess() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertDoesNotThrow(() -> service.makeDeposit(bankAccountModel.getAccountId(), BigDecimal.ONE));
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
        verify(bankAccountRepository).save(bankAccountModel);
        verify(transactionRepository).save(any());
    }

    @Test
    void shouldFailWhenMakeDepositDueToInexistantBankAccount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.empty());

        // Then
        assertThrows(BankAccountException.class,
            () -> service.makeDeposit(bankAccountModel.getAccountId(), BigDecimal.ONE)).getMessage()
            .equals("Bank Account identifier is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldFailWhenMakeDepositDueToNegativeDepositAmount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertThrows(BankAccountException.class,
            () -> service.makeDeposit(bankAccountModel.getAccountId(), BigDecimal.valueOf(-5))).getMessage()
            .equals("Deposit amount is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldMakeWithdrawalWithSuccess() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertDoesNotThrow(() -> service.makeWithdrawal(bankAccountModel.getAccountId(), BigDecimal.ONE));
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
        verify(bankAccountRepository).save(bankAccountModel);
        verify(transactionRepository).save(any());
    }

    @Test
    void shouldFailWhenMakeWithdrawalDueToInexistantBankAccount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.empty());

        // Then
        assertThrows(BankAccountException.class,
            () -> service.makeWithdrawal(bankAccountModel.getAccountId(), BigDecimal.ONE)).getMessage()
            .equals("Bank Account identifier is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldFailWhenMakeDepositDueToNegativeWithdrawalAmount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertThrows(BankAccountException.class,
            () -> service.makeWithdrawal(bankAccountModel.getAccountId(), BigDecimal.valueOf(-5))).getMessage()
            .equals("Deposit amount is not valid!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }

    @Test
    void shouldFailWhenMakeDepositDueToNegativeAccountBalanceAfterWithdrawalAmount() {
        // Given
        when(bankAccountRepository.findById(bankAccountModel.getAccountId())).thenReturn(Optional.of(bankAccountModel));

        // Then
        assertThrows(BankAccountException.class,
            () -> service.makeWithdrawal(bankAccountModel.getAccountId(), BigDecimal.valueOf(1000))).getMessage()
            .equals("Withdrawal exceeds account amount!");
        verify(bankAccountRepository).findById(bankAccountModel.getAccountId());
    }
}