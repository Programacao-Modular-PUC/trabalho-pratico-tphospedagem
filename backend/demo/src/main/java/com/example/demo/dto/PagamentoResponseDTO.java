package com.example.demo.dto;

import java.math.BigDecimal;
import com.example.demo.model.enums.TipoPagamento;

public record PagamentoResponseDTO(
    Long id,
    Long aluguelId,
    TipoPagamento tipo,
    BigDecimal valorOriginal,
    BigDecimal taxa,
    BigDecimal valorFinal,
    String resultado
) {}