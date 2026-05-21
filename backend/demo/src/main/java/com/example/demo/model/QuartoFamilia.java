package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("FAMILIA")
public class QuartoFamilia extends Quarto {

    private int quantidadeAmbientes;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorPorHospedeAdicional;

    @Column(precision = 10, scale = 2)
    private BigDecimal percentualDescontoGrupo;

    @Override
    public BigDecimal calcularValorDiaria(int quantidadeHospedes) {
        int excedente = Math.max(0, quantidadeHospedes - 2);
        BigDecimal valor = getValorBase()
            .add(valorPorHospedeAdicional.multiply(BigDecimal.valueOf(excedente)))
            .add(calcularAdicionaisConforto());

        if (quantidadeHospedes >= 4) {
            BigDecimal desconto = valor.multiply(percentualDescontoGrupo);
            return valor.subtract(desconto);
        }

        return valor;
    }
}