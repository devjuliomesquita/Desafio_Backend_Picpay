package com.juliomesquita.picpay_simplificado.core.transaction.application.services;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions.UnauthorizedTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
public class AuthorizerTransaction {
    private static final Logger log = LoggerFactory.getLogger(AuthorizerTransaction.class);
    private final RestClient restClient;

    public AuthorizerTransaction(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
                .build();
    }

    private record Authorization(String message){
        public Boolean isAuthorized(){
            return this.message.equals("Autorizado");
        }
    }

    public void execute(Transaction transaction){
        log.info("authorizing transaction :: {}", transaction);
        ResponseEntity<Authorization> response = this.restClient.get().retrieve().toEntity(Authorization.class);
        if(response.getStatusCode().isError() ||
                !Objects.requireNonNull(response.getBody()).isAuthorized()
        ){
            throw new UnauthorizedTransactionException("Transação não autorizada.");
        }
    }
}
