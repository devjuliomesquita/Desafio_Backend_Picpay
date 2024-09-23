package com.juliomesquita.picpay_simplificado.core.commom.utils;

import com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence.TransactionRepository;
import com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence.WalletEntity;
import com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class LoadDatabase implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
    private final WalletRepository walletRepository;

    public LoadDatabase(final WalletRepository walletRepository) {
        this.walletRepository = Objects.requireNonNull(walletRepository);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        try {
            WalletEntity walletSaved1 = this.walletRepository.save(
                    new WalletEntity(1L, "Júlio Mesquita", "37381122006", "julio@test.com", "password", 1, BigDecimal.valueOf(10000), null)
            );
            WalletEntity walletSaved2 = this.walletRepository.save(
                    new WalletEntity(2L, "Amanda Maria", "39538663039", "amanda@test.com", "password", 2, BigDecimal.valueOf(1000), null)
            );
            logger.info("Database created with 2 wallets :: {} and {}", walletSaved1.fullName(), walletSaved2.fullName());
        } catch (Throwable t) {
            logger.error("Error :: {}", t.getMessage(), t);
        }
    }
}
