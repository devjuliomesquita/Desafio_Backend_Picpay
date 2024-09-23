package com.juliomesquita.picpay_simplificado.core.transaction.infra.controller;

import com.juliomesquita.picpay_simplificado.core.transaction.infra.dtos.TransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Tag(name = "Transactions", description = "Criação de Buscar do histórico de trasações.")
public interface TransactionsDoc {
    @Operation(
            operationId = "createTransactions",
            summary = "Criar uma transação .",
            description = "Endpoint irá enviar a solicitação de uma transação que será validada e executada.",
            tags = {"Transactions"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad Request.")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Corpo da requisição",
                    content = @Content(
                            schema = @Schema(implementation = TransactionRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Pedido",
                                    summary = "Exemplo de corpo de pedido",
                                    value = TransactionRequest.requestExample))))
    @PostMapping(produces = {"application/json"})
    default ResponseEntity<?> createTransactions(
            @Parameter(name = "TransactionDTO", description = "Corpo da requisição", required = true)
            @RequestBody TransactionRequest request
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
