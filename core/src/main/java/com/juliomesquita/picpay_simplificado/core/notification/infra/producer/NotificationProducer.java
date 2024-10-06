package com.juliomesquita.picpay_simplificado.core.notification.infra.producer;

import com.juliomesquita.picpay_simplificado.core.notification.domain.constants.KafkaTopics;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(final KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void sendNotification(Transaction transaction){
        this.kafkaTemplate.send(KafkaTopics.TOPIC_NOTIFICATION_TRANSACTION, transaction);
    }
}
