package com.juliomesquita.picpay_simplificado.core.wallet.domain.entities;

import com.juliomesquita.picpay_simplificado.core.wallet.domain.enums.TypeWallet;

import java.math.BigDecimal;

public record Wallet(
        Long id,
        String fullName,
        String cpf,
        String email,
        String password,
        TypeWallet typeWallet,
        BigDecimal balance,
        Long version
) {
    /*
     * Métodos de dominio
     * */

    public Wallet debit(BigDecimal value) {
        return new Wallet(this.id, this.fullName, this.cpf, this.email, this.password, this.typeWallet,
                this.balance.subtract(value), this.version);
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(this.id, this.fullName, this.cpf, this.email, this.password, this.typeWallet,
                this.balance.add(value), this.version);
    }
}
