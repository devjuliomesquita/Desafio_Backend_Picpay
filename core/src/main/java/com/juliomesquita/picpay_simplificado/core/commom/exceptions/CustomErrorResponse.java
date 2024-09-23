package com.juliomesquita.picpay_simplificado.core.commom.exceptions;

import java.util.Map;

public record CustomErrorResponse(Map<String, String> errors) {
}
