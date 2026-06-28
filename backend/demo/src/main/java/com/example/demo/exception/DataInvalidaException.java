package com.example.demo.exception;

public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException(String message) {
        super(message);
    }
}