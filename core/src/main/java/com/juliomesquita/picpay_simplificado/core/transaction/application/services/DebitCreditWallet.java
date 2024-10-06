package com.juliomesquita.picpay_simplificado.core.transaction.application.services;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions.TransactionErrorException;
import com.juliomesquita.picpay_simplificado.core.wallet.domain.entities.Wallet;
import com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence.WalletEntity;
import com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class DebitCreditWallet {
    private static final Logger log = LoggerFactory.getLogger(DebitCreditWallet.class);
    private final WalletRepository walletRepository;

    public DebitCreditWallet(
            final WalletRepository walletRepository
    ) {
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }

    @Transactional
    public void execute(Transaction transaction) {
        log.info("Transaction sent: {}", transaction);
        Wallet walletPayer = this.walletRepository.findById(transaction.payer())
                .map(WalletEntity::restore)
                .orElseThrow(TransactionErrorException::new);
        Wallet walletPayee = this.walletRepository.findById(transaction.payee())
                .map(WalletEntity::restore)
                .orElseThrow(TransactionErrorException::new);

        WalletEntity walletPayerSaved = this.walletRepository.save(
                new WalletEntity(walletPayer.debit(transaction.value())));
        WalletEntity walletPayeeSaved = this.walletRepository.save(
                new WalletEntity(walletPayee.credit(transaction.value())));

        log.info("Wallet updated :: {} value: {} :: {} value: {}",
                walletPayerSaved.fullName(),
                walletPayerSaved.balance(),
                walletPayeeSaved.fullName(),
                walletPayeeSaved.balance());
    }
}
