package com.example.demo.model;

import java.math.BigDecimal;

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
    private BigDecimal taxaBerco;
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