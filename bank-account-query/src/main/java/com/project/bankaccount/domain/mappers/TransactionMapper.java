package com.project.bankaccount.domain.mappers;

import com.project.bankaccount.domain.dto.TransactionDto;
import com.project.bankaccount.domain.models.TransactionModel;
import org.springframework.beans.BeanUtils;

public class TransactionMapper {

    public TransactionModel convertToEntity(TransactionDto dto, Object... args) {
        TransactionModel entity = new TransactionModel();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    public TransactionDto convertToDto(TransactionModel entity, Object... args) {
        TransactionDto dto = new TransactionDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        // Set only necessary fields
        if (entity.getBankAccount() != null) {
            dto.setAccountId(entity.getBankAccount().getAccountId());
        }
        return dto;
    }
}
