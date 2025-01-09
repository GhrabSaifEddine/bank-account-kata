package com.project.bankaccount.infrastructure;

import com.project.bankaccount.domain.models.BankAccountModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccountModel, String> {
}
