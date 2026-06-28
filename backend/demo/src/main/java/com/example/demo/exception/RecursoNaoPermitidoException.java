package com.example.demo.exception;

public class RecursoNaoPermitidoException extends RuntimeException {

    public RecursoNaoPermitidoException(String message) {
        super(message);
    }

    public RecursoNaoPermitidoException(String recurso, String tipoQuarto) {
        super("Recurso não permitido para " + tipoQuarto + ": " + recurso);
    }
}
