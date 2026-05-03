package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
public class Aluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //gera o id automaticamente
    private Long id;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private int quantidadeDiarias;
    private double valorFinal;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Quarto quarto;
}
