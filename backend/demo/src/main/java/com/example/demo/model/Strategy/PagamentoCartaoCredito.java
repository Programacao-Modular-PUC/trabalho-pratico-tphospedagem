package com.example.demo.model.Strategy;

import java.math.BigDecimal;

public class PagamentoCartaoCredito implements MeioDePagamento {

    private static final BigDecimal TAXA = new BigDecimal("0.03"); // 3%

    @Override
    public BigDecimal calcularTaxa(BigDecimal valor) {
        return valor.multiply(TAXA);
    }

    @Override
    public String processar(BigDecimal valor) {
        BigDecimal total = valor.add(calcularTaxa(valor));
        return "Pagamento via Cartão de Crédito de R$ " + total + " processado (taxa de 3% incluída).";
    }

    @Override
    public String getNome() { return "CARTAO_CREDITO"; }
}