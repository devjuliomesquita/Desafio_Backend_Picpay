package com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence;

import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<TransactionEntity, Long> {
}
