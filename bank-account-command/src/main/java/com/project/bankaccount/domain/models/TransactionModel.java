package com.project.bankaccount.domain.models;


import com.project.bankaccount.domain.dto.TransactionType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionModel {
    @Id
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private BankAccountModel bankAccount;

    public TransactionModel() {
    }

    public TransactionModel(String transactionId, TransactionType type, BigDecimal amount, LocalDateTime transactionDate,
        BankAccountModel bankAccount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.bankAccount = bankAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccountModel getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountModel bankAccount) {
        this.bankAccount = bankAccount;
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
