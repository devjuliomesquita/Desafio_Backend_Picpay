package com.juliomesquita.picpay_simplificado.core.transaction.domain.entities;

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
}
