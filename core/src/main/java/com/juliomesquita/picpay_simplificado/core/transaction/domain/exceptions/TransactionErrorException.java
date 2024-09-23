package com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions;

public class TransactionErrorException extends RuntimeException {
    public TransactionErrorException(String message) {
        super(message);
    }

    public TransactionErrorException() {
        super("Falha na transação.");
    }
}
