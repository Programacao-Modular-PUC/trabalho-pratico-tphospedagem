package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AluguelRequestDTO(
    @NotNull(message = "Data de entrada é obrigatória") LocalDateTime dataEntrada,
    @NotNull(message = "Data de saída é obrigatória") LocalDateTime dataSaida,
    @NotNull(message = "Cliente é obrigatório") Long clienteId,
    @NotNull(message = "Quarto é obrigatório") Long quartoId,
    @Positive(message = "Quantidade de hóspedes deve ser positiva") int quantidadeHospedes
) {
}