package com.example.demo.model.Strategy;

import java.math.BigDecimal;

public interface MeioDePagamento {
    BigDecimal calcularTaxa(BigDecimal valor);
    String processar(BigDecimal valor);
    String getNome();
}