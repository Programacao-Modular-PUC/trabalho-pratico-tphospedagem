package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quarto {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String tipo;
    private double valorBase;
    private boolean possuiAr;
    private boolean possuiHidro;
    private int capacidade;
    
    @ManyToOne  //muitos quartos pertencem a uma residencia
    private Residencia residencia;
}
