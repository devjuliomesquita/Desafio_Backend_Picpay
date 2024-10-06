package com.juliomesquita.picpay_simplificado.core.transaction.domain.entities;

import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        Long id,
        Long payer,
        Long payee,
        BigDecimal value,
        LocalDateTime createdAt
) {
    public Transaction {
        value = value.setScale(2);
    }

    public static TransactionResponse toDto(Transaction transaction) {
        return new TransactionResponse(
                transaction.id(),
                transaction.payer(),
                transaction.payer(),
                transaction.value(),
                transaction.createdAt());
    }
}
