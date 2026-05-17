package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.model.enums.TipoQuarto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record QuartoRequestDTO(
    @NotNull(message = "Tipo do quarto é obrigatório") TipoQuarto tipo,
    @NotNull(message = "Id da residência é obrigatório") Long residenciaId,
    @NotNull(message = "Valor base é obrigatório") @Positive(message = "Valor base deve ser positivo") BigDecimal valorBase,
    boolean possuiAR,
    boolean possuiHidro,
    @Positive(message = "Capacidade máxima deve ser positiva") int capacidadeMaxima,
    int quantidadeCamasSolteiro,
    BigDecimal taxaCamaAdicional,
    boolean camaQueenKing,
    boolean permiteBerco,
    BigDecimal taxaBerco,
    BigDecimal taxaQueenKing,
    int quantidadeAmbientes,
    BigDecimal valorPorHospedeAdicional,
    BigDecimal percentualDescontoGrupo
) {
}