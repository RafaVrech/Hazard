package com.ibm.agenda.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hazard.utils.Resposta;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ObjetoNaoEcontratoException.class })
    protected ResponseEntity<Object> handleObjetoNaoEcontratoException(RuntimeException ex, WebRequest request) {
        ObjetoNaoEcontratoException exception = (ObjetoNaoEcontratoException) ex;
        Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
        return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}