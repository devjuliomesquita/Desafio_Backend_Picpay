package com.juliomesquita.picpay_simplificado.core.transaction.application.services;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.wallet.domain.enums.TypeWallet;
import com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateTransaction {
    private static final Logger log = LoggerFactory.getLogger(ValidateTransaction.class);
    private final WalletRepository walletRepository;

    public ValidateTransaction(final WalletRepository walletRepository) {
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }
    /*
    * Para a transação ser válida:
    *  - Quem enviou deve ter uma carteira comum
    *  - Quem enviou deve ter dinheiro suficiente para a trasação
    *  - Quem enviou não deve ser o mesmo que recebeu
    * */
    public void execute(Transaction transaction){
        log.info("Vali");
        this.walletRepository.findById(transaction.payer())
                .map(payer -> payer.typeWallet() == TypeWallet.COMUM.getValue() &&
                        payer.balance().compareTo(transaction.value()) >= 0 &&
                        !payer.id().equals(transaction.payee()))
                .orElseThrow(() -> new RuntimeException("Trasação inválida."));
        this.walletRepository.findById(transaction.payee())
                .orElseThrow(() -> new RuntimeException("Trasação inválida."));
    }
}
