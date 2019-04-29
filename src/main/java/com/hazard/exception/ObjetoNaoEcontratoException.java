package com.hazard.exception;

public class ObjetoNaoEcontratoException extends RuntimeException {
    private Integer code = 1;

    public ObjetoNaoEcontratoException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}
