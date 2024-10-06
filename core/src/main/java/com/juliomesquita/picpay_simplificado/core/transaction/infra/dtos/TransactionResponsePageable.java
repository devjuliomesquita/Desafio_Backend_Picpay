package com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos;

import com.juliomesquita.picpay_simplificado.core.commom.generics.PaginationGeneric;

import java.util.List;

public record TransactionResponsePageable(
        List<TransactionResponse> responseList,
        PaginationGeneric pagination
) {
}
