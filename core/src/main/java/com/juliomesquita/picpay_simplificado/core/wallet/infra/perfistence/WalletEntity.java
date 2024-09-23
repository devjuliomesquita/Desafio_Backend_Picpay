package com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence;

import com.juliomesquita.picpay_simplificado.core.wallet.domain.entities.Wallet;
import com.juliomesquita.picpay_simplificado.core.wallet.domain.enums.TypeWallet;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(value = "TB_WALLETS")
public record WalletEntity(
        @Id @Column(value = "WALLET_ID") Long id,
        @Column(value = "WALLET_FULL_NAME") String fullName,
        @Column(value = "WALLET_CPF") String cpf,
        @Column(value = "WALLET_EMAIL") String email,
        @Column(value = "WALLET_PASSWORD") String password,
        @Column(value = "WALLET_TYPE_WALLET") Integer typeWallet,
        @Column(value = "WALLET_BALANCE") BigDecimal balance,
        @Version @Column(value = "WALLET_VERSION") Long version
) {
    public WalletEntity(Wallet wallet) {
        this(wallet.id(), wallet.fullName(), wallet.cpf(), wallet.email(), wallet.password(),
                wallet.typeWallet().getValue(), wallet.balance(), wallet.version());
    }

    public static Wallet restore(WalletEntity entity) {
        return new Wallet(
                entity.id(), entity.fullName(), entity.cpf(), entity.email(), entity.password(),
                TypeWallet.restore(entity.typeWallet()), entity.balance(), entity.version()
        );
    }
}
