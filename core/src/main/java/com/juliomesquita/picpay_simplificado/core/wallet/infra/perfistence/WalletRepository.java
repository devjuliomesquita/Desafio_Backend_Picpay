package com.juliomesquita.picpay_simplificado.core.wallet.infra.perfistence;

import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<WalletEntity, Long> {
}
