package com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;

import java.math.BigDecimal;

//Adicionar as validações de campos
public record TransactionRequest(
        Long provedorId,
        Long destinatarioId,
        BigDecimal valor
) {
    public Transaction createTransaction(){
        return new Transaction(null, this.provedorId, this.destinatarioId, this.valor, null);
    }

    public static final String requestExample = "{ \"provedorId\": 1, \"destinatarioId\": 2, \"valor\": 100.50 }";
}
