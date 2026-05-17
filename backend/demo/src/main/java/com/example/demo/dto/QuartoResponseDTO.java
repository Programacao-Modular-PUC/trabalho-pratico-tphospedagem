package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.model.enums.TipoQuarto;

public record QuartoResponseDTO(
    Long id,
    TipoQuarto tipo,
    Long residenciaId,
    BigDecimal valorBase,
    boolean possuiAR,
    boolean possuiHidro,
    int capacidadeMaxima,
    Integer quantidadeCamasSolteiro,
    BigDecimal taxaCamaAdicional,
    Boolean camaQueenKing,
    Boolean permiteBerco,
    BigDecimal taxaBerco,
    BigDecimal taxaQueenKing,
    Integer quantidadeAmbientes,
    BigDecimal valorPorHospedeAdicional,
    BigDecimal percentualDescontoGrupo
) {
}