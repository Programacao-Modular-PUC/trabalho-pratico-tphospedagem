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
@DiscriminatorValue("INDIVIDUAL")
public class QuartoIndividual extends Quarto {

    private int quantidadeCamasSolteiro;

    @Column(precision = 10, scale = 2)
    private BigDecimal taxaCamaAdicional;

    @Override
    public BigDecimal calcularValorDiaria(int quantidadeHospedes) {
        int excedente = Math.max(0, quantidadeHospedes - 1);
        BigDecimal valorHospedes = taxaCamaAdicional.multiply(BigDecimal.valueOf(excedente));

        return getValorBase()
            .add(valorHospedes)
            .add(calcularAdicionaisConforto());
    }
}