package com.example.demo.exception;

public class CapacidadeExcedidaException extends RuntimeException {

    public CapacidadeExcedidaException(String message) {
        super(message);
    }

    public CapacidadeExcedidaException(int capacidadeMaxima, int quantidadeHospedes) {
        super("Capacidade máxima do quarto excedida. Limite: " + capacidadeMaxima
            + ", Tentativa: " + quantidadeHospedes);
    }
}
