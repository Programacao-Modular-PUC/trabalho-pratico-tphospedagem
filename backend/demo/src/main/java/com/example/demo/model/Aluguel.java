package com.example.demo.model;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

import com.example.demo.model.enums.AluguelStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //gera o id automaticamente
    private Long id;

    @NotNull(message = "Data de entrada é obrigatória")
    private LocalDateTime dataEntrada;

    @NotNull(message = "Data de saída é obrigatória")
    private LocalDateTime dataSaida;

    private int quantidadeDiarias;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorFinal;

    private String recibo;

    @Enumerated(EnumType.STRING)
    private AluguelStatus status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("alugueis")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    @JsonIgnoreProperties("residencia")
    private Quarto quarto;
}
