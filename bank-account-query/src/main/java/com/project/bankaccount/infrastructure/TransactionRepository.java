package com.project.bankaccount.infrastructure;

import com.project.bankaccount.domain.models.BankAccountModel;
import com.project.bankaccount.domain.models.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, String> {
    List<TransactionModel> findByBankAccount(BankAccountModel bankAccountModel);
}
