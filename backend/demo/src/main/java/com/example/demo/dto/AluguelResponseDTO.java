package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.model.enums.AluguelStatus;

public record AluguelResponseDTO(
    Long id,
    LocalDateTime dataEntrada,
    LocalDateTime dataSaida,
    int quantidadeDiarias,
    BigDecimal valorFinal,
    AluguelStatus status,
    String recibo,
    Long clienteId,
    String clienteNome,
    Long quartoId,
    String tipoQuarto,
    boolean pago
) {
}