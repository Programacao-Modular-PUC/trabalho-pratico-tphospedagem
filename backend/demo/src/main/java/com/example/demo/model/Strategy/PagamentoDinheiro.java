package com.example.demo.model.Strategy;

import java.math.BigDecimal;

public class PagamentoDinheiro implements MeioDePagamento {

    @Override
    public BigDecimal calcularTaxa(BigDecimal valor) {
        return BigDecimal.ZERO;
    }

    @Override
    public String processar(BigDecimal valor) {
        return "Pagamento em Dinheiro de R$ " + valor + " registrado com sucesso.";
    }

    @Override
    public String getNome() { return "DINHEIRO"; }
}