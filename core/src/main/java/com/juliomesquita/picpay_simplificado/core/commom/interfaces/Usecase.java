package com.juliomesquita.picpay_simplificado.core.commom.interfaces;

public interface Usecase<I, O> {
    O execute(I input);
}
