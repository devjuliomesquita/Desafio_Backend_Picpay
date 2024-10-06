package com.juliomesquita.picpay_simplificado.core.transaction.application.usecases;

import com.juliomesquita.picpay_simplificado.core.commom.generics.PaginationGeneric;
import com.juliomesquita.picpay_simplificado.core.commom.interfaces.Usecase;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.entities.Transaction;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionResponsePageable;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence.TransactionEntity;
import com.juliomesquita.picpay_simplificado.core.transaction.infra.persistence.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetTransaction implements Usecase<GetTransaction.Input, GetTransaction.Output> {
    private static final Logger log = LoggerFactory.getLogger(GetTransaction.class);
    private final TransactionRepository transactionRepository;

    public GetTransaction(final TransactionRepository transactionRepository) {
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }

    public record Input(Integer page, Integer perPage) {
    }

    public record Output(TransactionResponsePageable response) {
        public static PaginationGeneric restore(Page<?> page) {
            return PaginationGeneric.builder()
                    .page(page.getNumber())
                    .perPage(page.getSize())
                    .totalPages(page.getTotalPages())
                    .totalElements(page.getTotalElements())
                    .build();
        }
    }

    @Override
    public Output execute(Input input) {
        log.info("Find all transactions");
        Page<Transaction> transactionPage = this.transactionRepository.findAll(PageRequest.of(input.page(), input.perPage()))
                .map(TransactionEntity::restore);
        return new Output(new TransactionResponsePageable(
                transactionPage.getContent().stream().map(Transaction::toDto).toList(),
                Output.restore(transactionPage)
        ));
    }
}
