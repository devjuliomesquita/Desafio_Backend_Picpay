package com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.wallet.domain.entities.Wallet;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(value = "TB_TRANSACTIONS")
public record TransactionEntity(
        @Id @Column(value = "TRANSACTION_ID") Long id,
        @Column(value = "TRANSACTION_PAYER") Long payer,
        @Column(value = "TRANSACTION_PAYEE") Long payee,
        @Column(value = "TRANSACTION_VALUE") BigDecimal value,
        @Column(value = "TRANSACTION_CREATED_AT") LocalDateTime createdAt
) {
    public TransactionEntity(Transaction transaction) {
        this(transaction.id(), transaction.payer(), transaction.payee(), transaction.value(), LocalDateTime.now());
    }

    public static Transaction restore(TransactionEntity entity) {
        return new Transaction(
                entity.id(), entity.payer(), entity.payee(), entity.value(), entity.createdAt()
        );
    }
}