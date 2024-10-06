package com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull(message = "Id do Provedor é obrigatório.")
        Long provedorId,

        @NotNull(message = "Id do Destinatário é obrigatório.")
        Long destinatarioId,

        @NotNull(message = "Valor é obrigatório.")
        @Positive(message = "Valor deve ser maior que zero.")
        BigDecimal valor
) {
    public Transaction createTransaction(){
        return new Transaction(null, this.provedorId, this.destinatarioId, this.valor, null);
    }

    public static final String requestExample = "{ \"provedorId\": 1, \"destinatarioId\": 2, \"valor\": 100.50 }";
}
