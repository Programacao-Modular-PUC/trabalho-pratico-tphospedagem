package com.example.demo.model.Strategy;

import java.math.BigDecimal;

public class PagamentoCartaoDebito implements MeioDePagamento {

    private static final BigDecimal TAXA = new BigDecimal("0.01"); // 1%

    @Override
    public BigDecimal calcularTaxa(BigDecimal valor) {
        return valor.multiply(TAXA);
    }

    @Override
    public String processar(BigDecimal valor) {
        BigDecimal total = valor.add(calcularTaxa(valor));
        return "Pagamento via Cartão de Débito de R$ " + total + " processado (taxa de 1% incluída).";
    }

    @Override
    public String getNome() { return "CARTAO_DEBITO"; }
}