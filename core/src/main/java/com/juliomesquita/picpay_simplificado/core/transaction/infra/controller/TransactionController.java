package com.juliomesquita.picpay_simplificado.core.transaction.infra.controller;

import com.juliomesquita.picpay_simplificado.core.transaction.application.usecases.CreateTransaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {
    private final CreateTransaction createTransaction;

    public TransactionController(final CreateTransaction createTransaction) {
        this.createTransaction = Objects.requireNonNull(createTransaction);
    }

//    Post de criação
//    Get para listar as transações

}
