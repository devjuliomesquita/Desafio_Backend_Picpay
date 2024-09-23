package com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions;

public class UnauthorizedTransactionException extends RuntimeException{
    public UnauthorizedTransactionException() {
        super("Transaction Unauthorized.");
    }

    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
