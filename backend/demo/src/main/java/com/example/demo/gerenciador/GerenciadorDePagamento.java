package com.example.demo.gerenciador;

import com.example.demo.model.enums.TipoPagamento;
import com.example.demo.model.Strategy.*;

public class GerenciadorDePagamento {

    private static GerenciadorDePagamento instancia;

    private GerenciadorDePagamento() {}

    public static GerenciadorDePagamento getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorDePagamento();
        }
        return instancia;
    }

    public MeioDePagamento obterEstrategia(TipoPagamento tipo) {
        return switch (tipo) {
            case PIX             -> new PagamentoPix();
            case CARTAO_CREDITO  -> new PagamentoCartaoCredito();
            case CARTAO_DEBITO   -> new PagamentoCartaoDebito();
            case DINHEIRO        -> new PagamentoDinheiro();
        };
    }
}