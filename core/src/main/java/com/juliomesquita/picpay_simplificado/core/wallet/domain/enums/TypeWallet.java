package com.juliomesquita.picpay_simplificado.core.wallet.domain.enums;

import java.util.Arrays;

public enum TypeWallet {
    COMUM(1), LOJISTA(2);
    private int value;

    private TypeWallet(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeWallet restore(int value) {
        for (TypeWallet wallet : values()) {
            if (wallet.getValue() == value) {
                return wallet;
            }
        }
        throw new RuntimeException("TypeWallet :: Cod: %s invalid.".formatted(value));
    }
}
