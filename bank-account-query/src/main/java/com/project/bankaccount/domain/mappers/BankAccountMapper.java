package com.project.bankaccount.domain.mappers;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.models.BankAccountModel;
import org.springframework.beans.BeanUtils;

public class BankAccountMapper {

    public BankAccountModel convertToEntity(BankAccountDto dto, Object... args) {
        BankAccountModel entity = new BankAccountModel();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    public BankAccountDto convertToDto(BankAccountModel entity, Object... args) {
        BankAccountDto dto = new BankAccountDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
