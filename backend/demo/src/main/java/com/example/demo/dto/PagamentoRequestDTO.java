package com.example.demo.dto;

import com.example.demo.model.enums.TipoPagamento;

public record PagamentoRequestDTO(
    Long aluguelId,
    TipoPagamento tipo
) {}