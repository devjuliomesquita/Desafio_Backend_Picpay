package com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<TransactionEntity, Long>,
        ListCrudRepository<TransactionEntity, Long> {

}
