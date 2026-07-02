package com.example.demo.model.Strategy;

import java.math.BigDecimal;

public class PagamentoPix implements MeioDePagamento {

    @Override
    public BigDecimal calcularTaxa(BigDecimal valor) {
        return BigDecimal.ZERO; // PIX sem taxa
    }

    @Override
    public String processar(BigDecimal valor) {
        return "Pagamento via PIX de R$ " + valor + " processado com sucesso.";
    }

    @Override
    public String getNome() { return "PIX"; }
}