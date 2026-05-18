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
@DiscriminatorValue("DUPLO")
public class QuartoDuplo extends Quarto {

    private boolean camaQueenKing;
    private boolean permiteBerco;

    @Column(precision = 10, scale = 2)
    private BigDecimal taxaBerco;

    @Column(precision = 10, scale = 2)
    private BigDecimal taxaQueenKing;

    @Override
    public BigDecimal calcularValorDiaria(int quantidadeHospedes) {
        BigDecimal adicionais = BigDecimal.ZERO;

        if (camaQueenKing) {
            adicionais = adicionais.add(taxaQueenKing);
        }

        if (permiteBerco) {
            adicionais = adicionais.add(taxaBerco);
        }

        return getValorBase()
            .add(adicionais)
            .add(calcularAdicionaisConforto());
    }
}