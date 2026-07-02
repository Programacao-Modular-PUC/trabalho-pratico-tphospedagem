package com.example.demo.exception;

public class QuartoIndisponivelException extends RuntimeException {

    public QuartoIndisponivelException(Long quartoId) {
        super("Quarto " + quartoId + " está indisponível para o período informado");
    }

    public QuartoIndisponivelException(String message) {
        super(message);
    }
}
