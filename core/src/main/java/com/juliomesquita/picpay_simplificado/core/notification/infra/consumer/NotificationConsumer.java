package com.juliomesquita.picpay_simplificado.core.notification.infra.consumer;

import com.juliomesquita.picpay_simplificado.core.notification.domain.constants.KafkaTopics;
import com.juliomesquita.picpay_simplificado.core.notification.domain.exceptions.NotificationErrorException;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v1/notify")
                .build();
    }

    private record Data(String message) {
    }

    private record Notification(String status, Data data) {
        public Boolean isAuthorized() {
            return this.status.equals("error");
        }
    }

    @KafkaListener(topics = KafkaTopics.TOPIC_NOTIFICATION_TRANSACTION, groupId = "picpay-simplificado")
    public void execute(Transaction transaction) {
        log.info("Notifying transaction :: {}", transaction);

        ResponseEntity<Notification> response = this.restClient.post().retrieve().toEntity(Notification.class);

        if (response.getStatusCode().isError()) {
            throw new NotificationErrorException("Error notify transaction " + transaction);
        }
        log.info("Notification has been sent :: {}", transaction);
    }
}
