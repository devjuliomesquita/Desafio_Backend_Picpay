package com.juliomesquita.picpay_simplificado.core.commom.exceptions;

import com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions.TransactionErrorException;
import com.juliomesquita.picpay_simplificado.core.transaction.domain.exceptions.UnauthorizedTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // Request Validate
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse(errors));
    }

    @ExceptionHandler(TransactionErrorException.class)
    public ResponseEntity<CustomErrorResponse> handle(TransactionErrorException e) {
        Map<String, String> error = new HashMap<>();
        error.put("Transaction Error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse(error));
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<CustomErrorResponse> handle(UnauthorizedTransactionException e) {
        Map<String, String> error = new HashMap<>();
        error.put("Transaction Error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse(error));
    }
}
