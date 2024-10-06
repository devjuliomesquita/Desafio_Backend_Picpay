package com.juliomesquita.picpay_simplificado.core.transaction.application.usecases;

import com.juliomesquita.picpay_simplificado.core.commom.interfaces.Usecase;
import com.juliomesquita.picpay_simplificado.core.notification.application.services.NotificationService;
import com.juliomesquita.picpay_simplificado.core.transaction.application.services.AuthorizerTransaction;
import com.juliomesquita.picpay_simplificado.core.transaction.application.services.DebitCreditWallet;
import com.juliomesquita.picpay_simplificado.core.transaction.application.services.ValidateTransaction;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionRequest;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionResponse;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence.TransactionEntity;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
@Service
public class CreateTransaction implements Usecase<CreateTransaction.Input, CreateTransaction.Output> {
    private static final Logger log = LoggerFactory.getLogger(CreateTransaction.class);
    private final TransactionRepository transactionRepository;
    private final DebitCreditWallet debitCreditWallet;
    private final ValidateTransaction validateTransaction;
    private final AuthorizerTransaction authorizerTransaction;
    private final NotificationService notificationService;

    public CreateTransaction(
            final TransactionRepository transactionRepository,
            final DebitCreditWallet debitCreditWallet,
            final ValidateTransaction validateTransaction,
            final AuthorizerTransaction authorizerTransaction,
            final NotificationService notificationService
    ) {
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
        this.debitCreditWallet = Objects.requireNonNull(debitCreditWallet);
        this.validateTransaction = Objects.requireNonNull(validateTransaction);
        this.authorizerTransaction = Objects.requireNonNull(authorizerTransaction);
        this.notificationService = Objects.requireNonNull(notificationService);
    }

    public record Input(TransactionRequest request) {
    }

    public record Output(TransactionResponse response) {
    }

    @Override
    public Output execute(Input input) {
        Transaction transaction = input.request.createTransaction();
        this.validateTransaction.execute(transaction);
        Transaction transactionSaved = TransactionEntity.restore(
                this.transactionRepository.save(new TransactionEntity(transaction)));
        this.debitCreditWallet.execute(transaction);
        this.authorizerTransaction.execute(transaction);
        this.notificationService.notify(transactionSaved);
        return new Output(Transaction.toDto(transactionSaved));
    }


}
