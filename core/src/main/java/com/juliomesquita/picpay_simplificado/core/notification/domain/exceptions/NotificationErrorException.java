package com.juliomesquita.picpay_simplificado.core.notification.domain.exceptions;

public class NotificationErrorException extends RuntimeException {
    public NotificationErrorException() {
        super("Falha no envio da mensagem.");
    }

    public NotificationErrorException(String message) {
        super(message);
    }
}
