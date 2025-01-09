package com.project.bankaccount.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    String transactionId;
    String accountId;
    TransactionType type;
    BigDecimal amount;
    LocalDateTime transactionDate;

    public TransactionDto() {
    }

    public TransactionDto(String transactionId, String accountId, TransactionType type, BigDecimal amount,
        LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
