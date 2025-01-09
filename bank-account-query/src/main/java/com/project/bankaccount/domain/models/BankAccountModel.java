package com.project.bankaccount.domain.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account")
public class BankAccountModel {
    @Id
    private String accountId;
    private BigDecimal balance;
    private String clientName;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public BankAccountModel() {
    }

    public BankAccountModel(String accountId, BigDecimal amount, String clientName, LocalDateTime creationDate,
        LocalDateTime lastUpdateDate) {
        this.accountId = accountId;
        this.balance = amount;
        this.clientName = clientName;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}