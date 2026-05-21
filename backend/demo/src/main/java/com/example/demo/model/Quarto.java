package com.example.demo.model;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_quarto", discriminatorType = DiscriminatorType.STRING)
public abstract class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorBase;
    private boolean possuiAR;
    private boolean possuiHidro;
    private int capacidadeMaxima;
    
    @ManyToOne
    @JsonIgnoreProperties("quartos")
    private Residencia residencia;

    public abstract BigDecimal calcularValorDiaria(int quantidadeHospedes);

    protected BigDecimal calcularAdicionaisConforto() {
        BigDecimal adicional = BigDecimal.ZERO;

        if (possuiAR) {
            adicional = adicional.add(new BigDecimal("20.00"));
        }

        if (possuiHidro) {
            adicional = adicional.add(new BigDecimal("30.00"));
        }

        return adicional;
    }
}
