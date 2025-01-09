package com.project.bankaccount.domain.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccountDto {
        private String accountId;
        private BigDecimal balance;
        private String clientName;
        private LocalDateTime creationDate;
        private LocalDateTime lastUpdateDate;

        public BankAccountDto() {
        }

        public BankAccountDto(String accountId, BigDecimal balance, String clientName, LocalDateTime creationDate,
            LocalDateTime lastUpdateDate) {
                this.accountId = accountId;
                this.balance = balance;
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

        @Override
        public String toString() {
                return "BankAccountDto{" +
                    "accountId='" + accountId + '\'' +
                    ", balance=" + balance +
                    ", clientName='" + clientName + '\'' +
                    ", creationDate=" + creationDate +
                    ", lastUpdateDate=" + lastUpdateDate +
                    '}';
        }
}
