package com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        Long payer,
        Long payee,
        BigDecimal value,
        LocalDateTime createdAt
) {
}
