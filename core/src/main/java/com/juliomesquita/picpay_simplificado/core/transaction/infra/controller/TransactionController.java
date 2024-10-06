package com.juliomesquita.picpay_simplificado.core.transaction.infra.controller;

import com.juliomesquita.picpay_simplificado.core.transaction.application.usecases.CreateTransaction;
import com.juliomesquita.picpay_simplificado.core.transaction.application.usecases.GetTransaction;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionRequest;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionResponse;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionResponsePageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController implements  TransactionsDoc {
    private final CreateTransaction createTransaction;
    private final GetTransaction getTransaction;

    public TransactionController(
            final CreateTransaction createTransaction,
            final GetTransaction getTransaction

    ) {
        this.createTransaction = Objects.requireNonNull(createTransaction);
        this.getTransaction = Objects.requireNonNull(getTransaction);
    }

    @Override
    public ResponseEntity<TransactionResponse> createTransactions(TransactionRequest request) {
        CreateTransaction.Output result = this.createTransaction.execute(new CreateTransaction.Input(request));
        return ResponseEntity.ok(result.response());
    }

    @Override
    public ResponseEntity<TransactionResponsePageable> getTransactions(Integer page, Integer perPage) {
        GetTransaction.Output result = this.getTransaction.execute(new GetTransaction.Input(page, perPage));
        return ResponseEntity.ok(result.response());
    }
}
