package com.project.bankaccount.domain.dto;

import java.math.BigDecimal;

public class AmountDto {
    BigDecimal amount;

    public AmountDto() {
    }

    public AmountDto(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
