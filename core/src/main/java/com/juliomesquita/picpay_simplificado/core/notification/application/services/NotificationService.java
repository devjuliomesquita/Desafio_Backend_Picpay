package com.juliomesquita.picpay_simplificado.core.notification.application.services;

import com.juliomesquita.picpay_simplificado.core.notification.infra.producer.NotificationProducer;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationProducer notificationProducer;

    public NotificationService(final NotificationProducer notificationProducer) {
        this.notificationProducer = Objects.requireNonNull(notificationProducer);
    }

    public void notify(Transaction transaction) {
        log.info("Notification sent, transaction :: {}", transaction);
        this.notificationProducer.sendNotification(transaction);
    }
}
